package com.zsl.tools.codegenerator.config.rules;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:24 PM
 **/
public interface IColumnType {
    /**
     * <p>
     * 获取字段类型
     * </p>
     *
     * @return 字段类型
     */
    String getType();

    /**
     * <p>
     * 获取字段类型完整名
     * </p>
     *
     * @return 字段类型完整名
     */
    String getPkg();

}
