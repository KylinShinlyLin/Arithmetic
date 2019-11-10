package com.zsl.tools.codegenerator.config;

import com.zsl.tools.codegenerator.table.TableInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 11/8/2019 5:47 PM
 **/
@Data
@Accessors(chain = true)
public abstract class FileOutConfig {


    public FileOutConfig() {
        // to do nothing
    }


    /**
     * 输出文件
     */
    public abstract String outputFile(TableInfo tableInfo);
}
