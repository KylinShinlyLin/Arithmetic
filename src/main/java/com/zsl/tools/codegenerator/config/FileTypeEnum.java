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
    ENTITY(1, "实体文件", "", "entity", ".java"),
    MAPPER(2, "Mapper文件", "Mapper", "mapper", ".java"),
    XML(3, "XML文件", "Mapper", "xml", ".xml"),
    SERVICE(4, "Service文件", "Service", "service", ".java"),
    SERVICE_IMPL(5, "ServiceImpl文件", "ServiceImpl", "impl", ".java"),
    DTO(6, "DTO文件", "DTO", "dto", ".java"),
    VO(7, "VO文件", "VO", "vo", ".java"),
    PARAM(8, "Param文件", "QueryParamDTO", "param", ".java");

    @Getter
    private Integer value;

    @Getter
    private String describe;

    @Getter
    private String suffix;

    @Getter
    private String path;

    @Getter
    private String fileExtName;

    FileTypeEnum(Integer value, String describe, String suffix, String path, String fileExtName) {
        this.value = value;
        this.describe = describe;
        this.suffix = suffix;
        this.path = path;
        this.fileExtName = fileExtName;
    }

}
