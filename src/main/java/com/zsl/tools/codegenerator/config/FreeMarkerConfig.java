package com.zsl.tools.codegenerator.config;

import lombok.Builder;
import lombok.Data;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 6:36 PM
 **/
@Builder
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

    /**
     * 模板位置
     */
    private String templates;
}
