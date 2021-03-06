package com.sofyun.core.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName GeneratorUtils
 * @Description Mybatis 代码生成器
 * @Author gm
 * @Date 2019/2/18 15:34
 * @Version 1.0
 **/
@Data
public class GeneratorUtils implements Serializable {

    private static final long serialVersionUID = 2864644901961372086L;

    private String outputDir;

    private String author;

    private String dataUrl;

    private String driverName;

    private String username;

    private String password;

    private GlobalConfig globalConfig(){
        GlobalConfig global = new GlobalConfig();
        global.setOutputDir(outputDir);
        global.setFileOverride(false);
        global.setAuthor(author);
        global.setSwagger2(true);
        global.setOpen(false);
        global.setBaseResultMap(true);
        global.setBaseColumnList(true);
        global.setMapperName("%sMapper");
        global.setXmlName("%sMapper");
        global.setServiceName("%sService");
        global.setServiceImplName("%sServiceImpl");
        global.setControllerName("%sController");
        return global;
    }

    private DataSourceConfig dataSource(){
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl(dataUrl);
        dataSource.setDriverName(driverName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private PackageConfig packageConfig(){
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.sofyun.admin");
        packageConfig.setEntity("domain");
        packageConfig.setController("web");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper.xml");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        return packageConfig;
    }

    private StrategyConfig strategy(){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setVersionFieldName("version");
        return strategy;
    }

    public void run(){

        AutoGenerator autoGenerator = new AutoGenerator();

        // 1.全局配置
        autoGenerator.setGlobalConfig(globalConfig());

        // 2.数据源配置
        autoGenerator.setDataSource(dataSource());

        // 3.包名配置
        autoGenerator.setPackageInfo(packageConfig());

        // 4.表策略配置
        autoGenerator.setStrategy(strategy());

        // 5.配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());

        // 6.执行
        autoGenerator.execute();

    }

}


