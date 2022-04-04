package com.ah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1923:06
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置Docket bean实例
     * @return nick
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // enable 关闭swagger
                //.enable(false)
                // 分组
                .groupName("ahao")
                .select()
                // RequestHandlerSelectors 配置要扫描接口的方式
                // basePackage 指定扫描位置
                // any 扫描全部
                // none 都不扫描
                .apis(RequestHandlerSelectors.basePackage("com.ah.controller"))
                .build();
    }

    ///**
    // * 可以配置多个docket
    // * @return nick
    // */
    //@Bean
    //public Docket docket1() {
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .apiInfo(apiInfo())
    //            // enable 关闭swagger
    //            //.enable(false)
    //            // 分组
    //            .groupName("ahao2")
    //            .select()
    //            // RequestHandlerSelectors 配置要扫描接口的方式
    //            // basePackage 指定扫描位置
    //            // any 扫描全部
    //            // none 都不扫描
    //            .apis(RequestHandlerSelectors.basePackage("com.ah.controller"))
    //            .build();
    //}

    public ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("ahao", "https://blog.kuangstudy.com/", "1643977841@qq.com");

        return new ApiInfo(
                "AHao的Swagger2文档",
                "智慧生活",
                "1.0",
                "https://blog.kuangstudy.com/",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
