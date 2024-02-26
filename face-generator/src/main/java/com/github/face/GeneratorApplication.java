package com.github.face;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.github.face.config.GeneratorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代码生成启动类
 *
 * @author wo
 */
@SpringBootApplication
public class GeneratorApplication implements CommandLineRunner {

    @Autowired
    private GeneratorProperties generatorProperties;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GeneratorApplication.class, args);
        System.out.println("==> 代码已生成");
        // 关闭项目
        context.close();
    }

    @Override
    public void run(String... args) {
        //clean package
        String[] tables = generatorProperties.getTables().toArray(new String[0]);
        //自定义属性注入
        //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("modulePackage", generatorProperties.getModulePackageName());
                map.put("baseRoute", generatorProperties.getBaseRoute());
                map.put("routerPackage", generatorProperties.getControllerPackageName());
                this.setMap(map);
            }
        };

        // 自定义模版
        List<FileOutConfig> focList = new ArrayList<>();
        FileOutConfig queryDTOFileOutConfig = new FileOutConfig("/templates/queryRequest.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorProperties.getControllerPath() + "/request/" + tableInfo.getEntityName() + "QueryRequest.java";
            }
        };

        FileOutConfig addDTOFileOutConfig = new FileOutConfig("/templates/addRequest.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorProperties.getControllerPath() + "/request/" + tableInfo.getEntityName() + "AddRequest.java";
            }
        };

        FileOutConfig updateDTOFileOutConfig = new FileOutConfig("/templates/updateRequest.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorProperties.getControllerPath() + "/request/" + tableInfo.getEntityName() + "UpdateRequest.java";
            }
        };


        FileOutConfig pageConfig = new FileOutConfig("/templates/page.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String first = tableInfo.getEntityName().substring(0, 1);
                return generatorProperties.getFilePath() + "./front/views/" + tableInfo.getEntityName().replaceFirst(first, first.toLowerCase()) + "/" + "index.vue";
            }
        };

        FileOutConfig modalConfig = new FileOutConfig("/templates/modal.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String first = tableInfo.getEntityName().substring(0, 1);
                return generatorProperties.getFilePath() + "./front/views/" + tableInfo.getEntityName().replaceFirst(first, first.toLowerCase()) + "/components/" + "editModal.vue";
            }
        };

        FileOutConfig apiConfig = new FileOutConfig("/templates/api.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String first = tableInfo.getEntityName().substring(0, 1);
                return generatorProperties.getFilePath() + "./front/api/" + tableInfo.getEntityName().replaceFirst(first, first.toLowerCase()) + "/index.js";
            }
        };

        FileOutConfig controllerOutConfig = new FileOutConfig("/templates/AdminController.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorProperties.getControllerPath() + "/controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        };

        focList.add(queryDTOFileOutConfig);
        focList.add(addDTOFileOutConfig);
        focList.add(updateDTOFileOutConfig);
        focList.add(controllerOutConfig);
        // 前端文件
        focList.add(pageConfig);
        focList.add(modalConfig);
        focList.add(apiConfig);
        injectionConfig.setFileOutConfigList(focList);

        // 全剧配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setAuthor(generatorProperties.getAuthor())
//                .setSwagger2(true)
                .setOutputDir(generatorProperties.getFilePath())
                .setServiceName("%sService")
                .setFileOverride(true)
                .setOpen(false);
        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dataSourceProperties.getUrl())
                .setUsername(dataSourceProperties.getUsername())
                .setPassword(dataSourceProperties.getPassword())
                .setDriverName("com.mysql.cj.jdbc.Driver");

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        // 由于 controller 包名 和 其他包名不统一，所以全部单独设置
        packageConfig
                .setParent(generatorProperties.getModulePackageName())
                .setController(null);

        // 模版配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        // 控制是否生成 mapper.xml文件
        //templateConfig.setXml(null);
        templateConfig.setEntityKt(null);

        List<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("create_time", FieldFill.INSERT));
        fillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(generatorProperties.getTablePrefix().toArray(new String[0]))
                .setTableFillList(fillList)
                .setInclude(tables);

        final AutoGenerator autoGenerator = new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplate(templateConfig)
                .setCfg(injectionConfig);
        autoGenerator.execute();

    }
}
