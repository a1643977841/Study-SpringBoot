package com.ah.utils;//package com.ah.utils;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.fill.Column;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
///**
// * mybatis-plus代码生成器
// * @author LiuHao
// * @version V1.0
// * @date 2022/3/12 12:06 AM
// */
//public class MybatisPlusGenerator {
//    /**
//     * 数据库url
//     */
//    private static final String URL = "jdbc:mysql://localhost:3306/springboot-mjxy?useUnicode=true&useSSL=false&characterEncoding=utf8";
//    /**
//     * 数据库账号
//     */
//    private static final String USERNAME = "admin";
//    /**
//     * 数据库密码
//     */
//    private static final String PASSWORD = "liu5201314.";
//    public static void main(String[] args) {
//        generator();
//    }
//
//    private static void generator() {
//        FastAutoGenerator.create(URL,USERNAME,PASSWORD)
//                // 全局配置
//                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
//                // 包配置
//                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
//                // 策略配置
//                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
//                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
//                        .entityBuilder().enableLombok().addTableFills(
//                                new Column("create_time", FieldFill.INSERT)
//                        ).build())
//                /*
//                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
//                   .templateEngine(new BeetlTemplateEngine())
//                   .templateEngine(new FreemarkerTemplateEngine())
//                 */
//                .execute();
//    }
//    // 处理 all 情况
//    private static List<String> getTables(String tables) {
//        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
//    }
//}
