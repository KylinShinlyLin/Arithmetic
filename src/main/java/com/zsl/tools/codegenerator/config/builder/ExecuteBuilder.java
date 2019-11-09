package com.zsl.tools.codegenerator.config.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zsl.tools.codegenerator.config.*;
import com.zsl.tools.codegenerator.config.rules.NamingStrategy;
import com.zsl.tools.codegenerator.constants.StringPool;
import com.zsl.tools.codegenerator.querys.IDbQuery;
import com.zsl.tools.codegenerator.table.TableField;
import com.zsl.tools.codegenerator.table.TableInfo;
import com.zsl.tools.codegenerator.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:31 PM
 **/
public class ExecuteBuilder {
    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;

    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * SQL语句类型
     */
    private IDbQuery dbQuery;
    /**
     * SQL连接
     */
    private Connection connection;

    /**
     * FreeMarker模板
     */
    private Configuration configuration;

    /**
     * 文件输出配置
     */
    private FileOutConfig fileOutConfig;

    /**
     * 模板路径配置
     */
    private FreeMarkerConfig freeMarkerConfig;


    public ExecuteBuilder() {
        configuration = new Configuration();
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(ExecuteBuilder.class, StringPool.SLASH);
    }

    /**
     * <p>
     * 获取所有的数据库表信息
     * </p>
     */
    private List<TableInfo> getTablesInfo() {
        PreparedStatement preparedStatement = null;
        List<TableInfo> tableList = Lists.newArrayList();
        try {
            String tablesSql = dbQuery.tablesSql();
            preparedStatement = connection.prepareStatement(tablesSql);
            ResultSet results = preparedStatement.executeQuery();
            TableInfo tableInfo;
            while (results.next()) {
                String tableName = results.getString(dbQuery.tableName());
                if (StringUtils.isNotEmpty(tableName) && strategyConfig.getAimTableNames().contains(tableName)) {
                    //提取表注释
                    String tableComment = results.getString(dbQuery.tableComment());
                    if (strategyConfig.isSkipView() && "VIEW".equals(tableComment)) {
                        // 跳过视图
                        continue;
                    }
                    //组装表名称和表注释
                    tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    tableInfo.setComment(tableComment);
                    tableList.add(tableInfo);
                } else {
                    System.err.println("当前数据库为空！！！");
                }
            }
            tableList.forEach(ti -> convertTableFields(ti, strategyConfig.getColumnNaming()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return processTable(tableList, strategyConfig.getColumnNaming());
    }

    /**
     * <p>
     * 将字段信息与表信息关联,查找字段信息
     * </p>
     *
     * @param tableInfo 表信息
     * @param strategy  命名策略
     * @return
     */
    private TableInfo convertTableFields(TableInfo tableInfo, NamingStrategy strategy) {
        //是否找到主键
        boolean haveId = false;
        List<TableField> fieldList = new ArrayList<>();
        List<TableField> commonFieldList = new ArrayList<>();
        try {
            String tableFieldsSql = String.format(dbQuery.tableFieldsSql(), tableInfo.getName());
            PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                TableField field = new TableField();
                String key = results.getString(dbQuery.fieldKey());
                // 判断是否是主键
                boolean isId = StringUtils.isNotEmpty(key) && "PRI".equals(key.toUpperCase());
                // 处理ID
                if (isId && !haveId) {
                    field.setKeyFlag(true);
                    //判断是否是自增主键
                    if (dbQuery.isKeyIdentity(results)) {
                        field.setKeyIdentityFlag(true);
                    }
                    haveId = true;
                } else {
                    field.setKeyFlag(false);
                }
                // 自定义字段查询
                String[] fcs = dbQuery.fieldCustom();
                if (null != fcs) {
                    Map<String, Object> customMap = new HashMap<>();
                    for (String fc : fcs) {
                        customMap.put(fc, results.getObject(fc));
                    }
                    field.setCustomMap(customMap);
                }
                // 查找字段名字
                field.setName(results.getString(dbQuery.fieldName()));
                //字段数据类型
                field.setType(results.getString(dbQuery.fieldType()));
                //映射java字段名称
                field.setPropertyName(strategyConfig, processName(field.getName(), strategy, null));
                //数据库字段映射Java字段
                field.setColumnType(dataSourceConfig.getTypeConvert().processTypeConvert(field.getType()));
                //设置评论
                field.setComment(results.getString(dbQuery.fieldComment()));
                fieldList.add(field);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception：" + e.getMessage());
        }
        tableInfo.setFields(fieldList);
        tableInfo.setCommonFields(commonFieldList);
        return tableInfo;
    }


    /**
     * <p>
     * 表名匹配
     * </p>
     *
     * @param setTableName 设置表名
     * @param dbTableName  数据库表单
     * @return
     */
    private boolean tableNameMatches(String setTableName, String dbTableName) {
        return setTableName.equals(dbTableName)
                || StringUtils.matches(setTableName, dbTableName);
    }


    /**
     * <p>
     * 处理表对应的类名称
     * </P>
     *
     * @param tableList 表名称
     * @param strategy  命名策略
     * @return 补充完整信息后的表
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, NamingStrategy strategy) {
        for (TableInfo tableInfo : tableList) {
            String entityName = NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, null));
            tableInfo.setEntityName(entityName);
            tableInfo.setServiceName(entityName + "Service");
            tableInfo.setServiceImplName(entityName + "ServiceImpl");
            tableInfo.setXmlName(entityName + "Mapper");
            tableInfo.setMapperName(entityName + "Mapper");
            tableInfo.setControllerName(entityName + "Controller");
            tableInfo.setVoName(entityName + "VO");
            tableInfo.setDtoName(entityName + "DTO");
            tableInfo.setParamName(entityName + "QueryParamDTO");
        }
        return tableList;
    }


    /**
     * <p>
     * 处理表/字段名称
     * </p>
     *
     * @param name
     * @param strategy
     * @param prefix
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy, String[] prefix) {
        boolean removePrefix = false;
        if (prefix != null && prefix.length >= 1) {
            removePrefix = true;
        }
        String propertyName;
        if (removePrefix) {
            if (strategy == NamingStrategy.underline_to_camel) {
                // 删除前缀、下划线转驼峰
                propertyName = NamingStrategy.removePrefixAndCamel(name, prefix);
            } else {
                // 删除前缀
                propertyName = NamingStrategy.removePrefix(name, prefix);
            }
        } else if (strategy == NamingStrategy.underline_to_camel) {
            // 下划线转驼峰
            propertyName = NamingStrategy.underlineToCamel(name);
        } else {
            // 不处理
            propertyName = name;
        }
        return propertyName;
    }


    public void outPutFile() {
        try {
            //TODO 文件路径
            List<TableInfo> tableInfoList = this.getTablesInfo();
            Map<String, Map<FileTypeEnum, String>> allPathInfo = this.getPathInfo();
            for (TableInfo tableInfo : tableInfoList) {
                Map<FileTypeEnum, String> pathInfo = allPathInfo.get(tableInfo.getName());
                Map<String, Object> objectMap = this.getObjectMap(tableInfo);
                Map<FileTypeEnum, String> templatePathMap = this.getTemplatePathMap();
                for (Map.Entry<FileTypeEnum, String> temp : pathInfo.entrySet()) {
                    checkNotExistCreate(temp.getValue());
                    //TODO freemarker 模板
                    writer(objectMap, templatePathMap.get(temp.getKey()), fileOutConfig.outputFile(tableInfo) + ".java");
                }
            }
        } catch (Exception e) {
            System.err.println("无法创建文件，请检查配置信息！");
            e.printStackTrace();
        }
    }

    private Map<FileTypeEnum, String> getTemplatePathMap() {
        Map<FileTypeEnum, String> typeEnumStringMap = Maps.newHashMap();
        typeEnumStringMap.put(FileTypeEnum.ENTITY, freeMarkerConfig.getEntityPath());
        typeEnumStringMap.put(FileTypeEnum.MAPPER, freeMarkerConfig.getMapperPath());
        typeEnumStringMap.put(FileTypeEnum.XML, freeMarkerConfig.getXmlPath());
        typeEnumStringMap.put(FileTypeEnum.SERVICE, freeMarkerConfig.getServicePath());
        typeEnumStringMap.put(FileTypeEnum.SERVICE_IMPL, freeMarkerConfig.getServiceImplPath());
        typeEnumStringMap.put(FileTypeEnum.DTO, freeMarkerConfig.getDtoPath());
        typeEnumStringMap.put(FileTypeEnum.VO, freeMarkerConfig.getVoPath());
        typeEnumStringMap.put(FileTypeEnum.PARAM, freeMarkerConfig.getParamPath());
        return typeEnumStringMap;
    }


    private Map<String, Map<FileTypeEnum, String>> getPathInfo() {
        Map<String, Map<FileTypeEnum, String>> pathInfoMap = Maps.newHashMap();
        for (String tableName : strategyConfig.getAimTableNames()) {
            pathInfoMap.put(tableName, Stream.of(FileTypeEnum.values())
                    .collect(Collectors.toMap(e -> e, FileTypeEnum::getPath)));
        }
        return pathInfoMap;
    }

    /**
     * 判断文件是否创建,不存在则创建
     *
     * @return
     */
    private void checkNotExistCreate(String filePath) {
        // 全局判断【默认】
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * <p>
     * 渲染对象 MAP 信息
     * </p>
     *
     * @param tableInfo 表信息对象
     * @return
     */
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = new HashMap<>(30);
        //相关配置
        objectMap.put("strategyConfig", strategyConfig);
        //包信息
        objectMap.put("package", strategyConfig.getPackageInfo());
        //作者
        objectMap.put("author", strategyConfig.getAuthor());
        //主键类型信息
        objectMap.put("idType", strategyConfig.getIdType());
        objectMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //表信息
        objectMap.put("table", tableInfo);
        //表实体名称
        objectMap.put("entity", tableInfo.getEntityName());
        objectMap.put("superEntityClass", "Model");
        objectMap.put("superMapperClass", "BaseMapper");
        objectMap.put("superServiceClass", "IService");
        objectMap.put("superServiceImplClass", "ServiceImpl");
        return objectMap;
    }

    /**
     * 写出文件到
     *
     * @param objectMap
     * @param templatePath
     * @param outputFile
     * @throws Exception
     */
    private void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        fileOutputStream.close();
    }


}
