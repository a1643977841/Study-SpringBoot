package com.ah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvn配置类
 *
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 3:39 PM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义视图
     *
     * @param registry nick
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login1.html");
        registry.addViewController("/login1.html").setViewName("login1");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                // 拦截所有请求
                .addPathPatterns("/**")
                // 排除这些请求
                .excludePathPatterns("/login1.html", "/", "/login","/css/**","/js/**","/images/**","/fonts/**");
    }
}
