package com.holidaystatistics.service.DownExcelService;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author nitmali@126.com
 * @date 2018/5/3 21:28
 */


public class ExcelConfig {
    @ConfigurationProperties(prefix="holidayExcel")
    @Component
    public static class HolidayExcelConfig {
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

        public InputStream getTemplate() throws IOException {
            Connection.Response txtSecretCodeResponse = Jsoup
                    .connect(templatePath)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

            byte[] templateByte = txtSecretCodeResponse.bodyAsBytes();

            return new ByteArrayInputStream(templateByte);


        }
    }

    @ConfigurationProperties(prefix="NotArrivedExcel")
    @Component
    public static class NotArrivedExcelConfig{

    }
}

