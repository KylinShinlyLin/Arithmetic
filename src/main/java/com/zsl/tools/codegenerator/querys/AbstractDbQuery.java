package com.zsl.tools.codegenerator.querys;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:41 PM
 **/
public abstract class AbstractDbQuery implements IDbQuery {
    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return false;
    }


    @Override
    public String[] fieldCustom() {
        return null;
    }
}
