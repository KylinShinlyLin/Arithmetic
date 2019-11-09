package com.zsl.tools.codegenerator.config;

import lombok.Data;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 6:36 PM
 **/
@Data
public class FreeMarkerConfig {

    private String entityPath;

    private String servicePath;

    private String serviceImplPath;

    private String dtoPath;

    private String voPath;

    private String mapperPath;

    private String xmlPath;

    private String paramPath;
}
