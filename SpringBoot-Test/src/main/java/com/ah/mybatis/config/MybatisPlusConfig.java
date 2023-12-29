package com.ah.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2023/11/10 23:41
 */
@Configuration
public class MybatisPlusConfig {


    /**
     * SQL执行效率插件
     * @return SQL执行效率插件
     */
    @Bean
    public PerformanceInterceptor persistenceInterceptor() {
        return new PerformanceInterceptor();
    }


    /**
     * 分页插件
     * @return 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
