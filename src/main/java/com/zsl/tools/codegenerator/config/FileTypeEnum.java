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
    ENTITY(1, "实体文件", "", "entity"),
    MAPPER(2, "Mapper文件", "Mapper", "mapper"),
    XML(3, "XML文件", "Mapper", "xml"),
    SERVICE(4, "Service文件", "Service", "service"),
    SERVICE_IMPL(5, "ServiceImpl文件", "ServiceImpl", "impl"),
    DTO(6, "DTO文件", "DTO", "dto"),
    VO(7, "VO文件", "VO", "vo"),
    PARAM(8, "Param文件", "QueryParamDTO", "param");

    @Getter
    private Integer value;

    @Getter
    private String describe;

    @Getter
    private String suffix;

    @Getter
    private String path;

    FileTypeEnum(Integer value, String describe, String suffix, String path) {
        this.value = value;
        this.describe = describe;
        this.suffix = suffix;
        this.path = path;
    }

}
