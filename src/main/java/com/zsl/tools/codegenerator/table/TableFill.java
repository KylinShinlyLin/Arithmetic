package com.zsl.tools.codegenerator.table;

import lombok.Data;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:21 PM
 **/
@Data
public class TableFill {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 忽略类型
     */
    private FieldFill fieldFill;

    private TableFill() {
        // to do nothing
    }

    public TableFill(String fieldName, FieldFill ignore) {
        this.fieldName = fieldName;
        this.fieldFill = ignore;
    }
}
