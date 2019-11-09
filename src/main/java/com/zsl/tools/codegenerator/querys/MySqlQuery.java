package com.zsl.tools.codegenerator.querys;

import com.zsl.tools.codegenerator.config.DbType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:42 PM
 **/
public class MySqlQuery extends AbstractDbQuery {

    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }


    @Override
    public String tablesSql() {
        return "show table status";
    }


    @Override
    public String tableFieldsSql() {
        return "show full fields from `%s`";
    }


    @Override
    public String tableName() {
        return "NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENT";
    }


    @Override
    public String fieldName() {
        return "FIELD";
    }


    @Override
    public String fieldType() {
        return "TYPE";
    }


    @Override
    public String fieldComment() {
        return "COMMENT";
    }


    @Override
    public String fieldKey() {
        return "KEY";
    }


    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("Extra"));
    }

}
