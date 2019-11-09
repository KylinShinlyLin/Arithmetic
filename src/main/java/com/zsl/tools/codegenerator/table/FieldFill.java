package com.zsl.tools.codegenerator.table;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:22 PM
 **/
public enum FieldFill {
    /**
     * 默认不处理
     */
    DEFAULT,
    /**
     * 插入填充字段
     */
    INSERT,
    /**
     * 更新填充字段
     */
    UPDATE,
    /**
     * 插入和更新填充字段
     */
    INSERT_UPDATE
}
