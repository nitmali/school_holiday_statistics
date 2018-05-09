package com.example.holidaystatistics.service.DownExcelService;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nitmali@126.com
 * @date 2018/5/3 21:28
 */

@ConfigurationProperties(prefix="excel")
@Component
public class ExcelConfig {
    private String className;

    private String excelName;

    private String templatePath;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
