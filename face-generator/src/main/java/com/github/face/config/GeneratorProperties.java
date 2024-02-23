package com.github.face.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "generate")
public class GeneratorProperties {

    private String basePackage;

    /**
     * 代码生成文件顶级输出目录
     */
    @Getter
    private String filePath;

    private String modulePackage;

    private String routerPackage;

    private String baseRoute;

    private List<String> tables;

    private List<String> tablePrefix = new ArrayList<>();

    private String author;

    public String getModulePackageName() {
        return getFilePath() + "/" + getBasePackage() + "." + modulePackage;
    }

    public String getModulePath() {
        return getFilePath() + "/module/" + getModulePackageName().replace(".", "/");
    }

    public String getControllerPackageName() {
        return getFilePath() + "." + basePackage + "." + routerPackage;
    }

    public String getControllerPath() {
        return getControllerPackageName().replace(".", "/");
    }

}
