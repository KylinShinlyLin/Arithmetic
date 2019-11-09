package com.zsl.tools.codegenerator.config.type;

import com.zsl.tools.codegenerator.config.rules.IColumnType;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:43 PM
 **/
public interface ITypeConvert {

    /**
     * <p>
     * 执行类型转换
     * </p>
     *
     * @param fieldType 字段类型
     * @return
     */
    IColumnType processTypeConvert(String fieldType);

}
