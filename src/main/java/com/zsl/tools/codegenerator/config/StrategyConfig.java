package com.zsl.tools.codegenerator.config;

import com.zsl.tools.codegenerator.config.rules.NamingStrategy;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 4:21 PM
 **/
@Builder
@Data
public class StrategyConfig {


    /**
     * 目标表名
     */
    private List<String> aimTableNames;

    /**
     * 表策略
     */
    private NamingStrategy columnNaming;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 包信息
     */
    private String packageInfo;


    /**
     * 指定生成的主键的ID类型
     */
    private IdType idType;
}
