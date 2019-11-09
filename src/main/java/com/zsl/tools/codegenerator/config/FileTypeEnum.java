package com.zsl.tools.codegenerator.config;

import lombok.Getter;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 5:49 PM
 **/
public enum FileTypeEnum {
    /**
     * 枚举
     */
    ENTITY(1, "实体文件", ""),
    MAPPER(2, "Mapper文件", "Mapper"),
    XML(3, "XML文件", "Mapper"),
    SERVICE(4, "Service文件", "Service"),
    SERVICE_IMPL(5, "ServiceImpl文件", "ServiceImpl"),
    DTO(6, "DTO文件", "DTO"),
    VO(7, "VO文件", "VO"),
    PARAM(8, "Param文件", "QueryParamDTO");

    @Getter
    private Integer value;

    @Getter
    private String describe;

    @Getter
    private String suffix;

    FileTypeEnum(Integer value, String describe, String suffix) {
        this.value = value;
        this.describe = describe;
        this.suffix = suffix;
    }

}
