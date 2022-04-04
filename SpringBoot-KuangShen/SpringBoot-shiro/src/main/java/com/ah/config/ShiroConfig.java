package com.ah.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1800:25
 */
@Configuration
public class ShiroConfig {
    /**
     * @param efaultWebSecurityManager nick
     * @return nick
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager efaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(efaultWebSecurityManager);
        /*
         添加shiro内置过滤取
         anon 无需认证即可访问
         authc 必须认证之后才可以访问
         user 必须拥有记住我功能才能用
         perms 拥有对某个资源的权限才能访问
         */
        Map<String, String> filterChainDefinition = new LinkedHashMap<>();
        filterChainDefinition.put("/user/addUser","perms[user:add]");
        filterChainDefinition.put("/user/updateUser","perms[user:update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        // 设置登陆请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 设置没有授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/401.html");
        return shiroFilterFactoryBean;
    }

    /**
     *
     * @param userRealm nick
     * @return nick
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联userRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }


    /**
     * 创建realm对象 需要自定义类
     * @return nick
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * 整合thymeleaf-shiro
     * @return nick
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
