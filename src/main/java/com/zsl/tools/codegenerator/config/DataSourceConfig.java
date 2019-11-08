package com.zsl.tools.codegenerator.config;

import com.zsl.tools.codegenerator.config.type.ITypeConvert;
import com.zsl.tools.codegenerator.config.type.MySqlTypeConvert;
import com.zsl.tools.codegenerator.querys.IDbQuery;
import com.zsl.tools.codegenerator.querys.MySqlQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:35 PM
 **/
public class DataSourceConfig {

    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery;
    /**
     * 数据库类型
     */
    private DbType dbType;
    /**
     * PostgreSQL schemaName
     */
    private String schemaName;
    /**
     * 类型转换
     */
    private ITypeConvert typeConvert;
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;

    public IDbQuery getDbQuery() {
        return new MySqlQuery();
    }

    /**
     * 判断数据库类型
     *
     * @return 类型枚举值
     */
    public DbType getDbType() {
        if (null == dbType) {
            if (driverName.contains("mysql")) {
                dbType = DbType.MYSQL;
            } else if (driverName.contains("oracle")) {
                dbType = DbType.ORACLE;
            } else if (driverName.contains("postgresql")) {
                dbType = DbType.POSTGRE_SQL;
            } else if (driverName.contains("db2")) {
                dbType = DbType.DB2;
            } else if (driverName.contains("mariadb")) {
                dbType = DbType.MARIADB;
            } else {
                throw new RuntimeException("Unknown type of database!");
            }
        }
        return dbType;
    }

    public ITypeConvert getTypeConvert() {
        return new MySqlTypeConvert();
    }

    /**
     * 创建数据库连接对象
     *
     * @return Connection
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
