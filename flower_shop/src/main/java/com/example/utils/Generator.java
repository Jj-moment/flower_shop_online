package com.example.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @author 蒋蒋
 */
public class Generator {
    public static void main(String[] args) {
        /*用户名*/
        String username = "root";
        /*密码*/
        String password = "123456";
        /*数据库名*/
        String dataBaseName = "flower_shop_online";
        /*作者名称*/
        String author = "Jj";
        /*项目名称*/
        String projectName = "flower_shop";
        /*父包名*/
        String parent = "com.example";
        /*调用代码生成方法*/
        generator(username, password, dataBaseName, author, projectName, parent);
    }

    /**代码生成方法*/
    public static void generator(String username, String password,
                                 String dataBaseName, String author,
                                 String projectName, String parent){
        FastAutoGenerator
                /*配置数据源*/
                .create("jdbc:mysql://localhost:3306/"+dataBaseName, username, password)
                /*基本设置*/
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖文件
                            .commentDate("yyyy-MM-dd hh:mm:ss")//注释日期
                            .outputDir(System.getProperty("user.dir") + "/"+projectName+"/src/main/java") // 指定输出目录
                            .disableOpenDir();   //禁止打开输出目录,默认打开
                })
                /*包设置*/
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
//                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir")+"/"+projectName+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                /*配置策略*/
                .strategyConfig(builder -> {
                    /*设置需要生成的表名*/
                    builder .addInclude("role_admin")
                            /*设置过滤表前缀*/
                            .addTablePrefix("sys_")
                            /*实体类配置策略*/
                            .entityBuilder()
                            /*开启lombok*/
                            .enableLombok()
                            /*
                            添加表字段填充,
                            "create_time"字段自动填充为插入时间,
                            "update_time"字段自动填充为插入修改时间
                            */
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE))
                            /*配置service层*/
                            .serviceBuilder()
                            /*格式化名称*/
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl");
                })
                /*模板配置*/
                .templateConfig(builder -> {
                    builder
                        /*禁用其他模板*/
                        /*.disable(TemplateType.ENTITY)*/
                        /*自定义controller模板*/
                        .controller("/templates/controller.java");
                })
                /*使用Freemarker引擎模板，默认的是Velocity引擎模板*/
                /*.templateEngine(new FreemarkerTemplateEngine())*/
                .execute();
    }
}