package com.zsl.tools.codegenerator.table;

import com.zsl.tools.codegenerator.config.StrategyConfig;
import com.zsl.tools.codegenerator.config.rules.DbColumnType;
import com.zsl.tools.codegenerator.config.rules.IColumnType;

import java.util.Map;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:16 PM
 **/
public class TableField {

    private boolean convert;
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    private String name;
    private String type;
    private String propertyName;
    private IColumnType columnType;
    private String comment;
    /**
     * 自定义查询字段列表
     */
    private Map<String, Object> customMap;

    public boolean isConvert() {
        return convert;
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }


    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public boolean isKeyIdentityFlag() {
        return keyIdentityFlag;
    }

    public void setKeyIdentityFlag(boolean keyIdentityFlag) {
        this.keyIdentityFlag = keyIdentityFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
    }

    public IColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(IColumnType columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 按JavaBean规则来生成get和set方法
     */
    public String getCapitalName() {
        if (propertyName.length() <= 1) {
            return propertyName.toUpperCase();
        }
        String setGetName = propertyName;
        if (DbColumnType.BASE_BOOLEAN.getType().equalsIgnoreCase(columnType.getType())) {
            //setGetName = StringUtils.removeIsPrefixIfBoolean(setGetName, Boolean.class);
        }
        // 第一个字母 小写、 第二个字母 大写 ，特殊处理
        String firstChar = setGetName.substring(0, 1);
        if (Character.isLowerCase(firstChar.toCharArray()[0])
                && Character.isUpperCase(setGetName.substring(1, 2).toCharArray()[0])) {
            return firstChar.toLowerCase() + setGetName.substring(1);
        }
        return firstChar.toUpperCase() + setGetName.substring(1);
    }

    public Map<String, Object> getCustomMap() {
        return customMap;
    }

    public void setCustomMap(Map<String, Object> customMap) {
        this.customMap = customMap;
    }
}
