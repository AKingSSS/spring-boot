package com.aking.learn;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("aking");
        // 是否打开资源管理器
        gc.setOpen(false);
        // 是否覆盖
        gc.setFileOverride(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        // 默认带I前缀
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sService");
        gc.setControllerName("com.aking.learn.controller");
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);

        mpg.setGlobalConfig(gc);

        // 2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://39.98.238.47:3339/Schema?characterEncoding=utf-8&useUnicode=true&allowMultiQueries=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.aking.learn");
        pc.setEntity("domain");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);

        // 3、模板配置????
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
         templateConfig.setEntity("templates/beanTemplate");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 支持 lombok
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        // 逻辑删除的标识
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充
        TableFill tf1 = new TableFill("create_time", FieldFill.INSERT);
        TableFill tf2 = new TableFill("modified_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        strategy.setTableFillList(list);
        // 需要映射的表
        strategy.setInclude("user".split(","));

        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
