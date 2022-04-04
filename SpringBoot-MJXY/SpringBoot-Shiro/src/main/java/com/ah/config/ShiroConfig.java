package com.ah.config;

import com.ah.realm.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 12:20 AM
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题，
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，
     * 以为在ShiroFilterFactoryBean的时候需要注入：
     * SecurityManager Filter Chain定义说明
     * 1 一个URL可以配置多个Filter，使用逗号隔开，
     * 2 当设置多个过滤器时，全部验证通过，才视为通过，
     * 3 部分过滤器可以指定参数，如perms，roles
     * @return nick
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须配置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String,String> filterChainDefinition = new LinkedHashMap<>();
        filterChainDefinition.put("/login","anon");
        // authc:所有url都必须认证通过才可以访问，anon 所有url都可以匿名访问
        filterChainDefinition.put("/**","authc");
        // 如果不设置默认会自动寻找web工程根目录下的/login.jsp 页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, Filter> filters = new HashMap<>();
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启controller注解
     * @return nick
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(getMemorySessionDAO());
        defaultWebSessionManager.setGlobalSessionTimeout(4200000);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        defaultWebSessionManager.setSessionIdCookie(getSimpleCookie());
        return defaultWebSessionManager;
    }
    @Bean
    public SessionDAO getMemorySessionDAO() {
        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
        memorySessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator());
        return memorySessionDAO;
    }
    @Bean
    public SessionIdGenerator javaUuidSessionIdGenerator() {
        return  new JavaUuidSessionIdGenerator();
    }
    @Bean
    public Cookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("security.session.id");
        simpleCookie.setPath("/");
        return simpleCookie;
    }
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWegSecurityManager = new DefaultWebSecurityManager();
        defaultWegSecurityManager.setRealm(userRealm);
        // 用户授权/认证信息Cache，采用EhCache缓存
        defaultWegSecurityManager.setCacheManager(getEhCacheManager());
        defaultWegSecurityManager.setSessionManager(getDefaultWebSessionManager());
        return defaultWegSecurityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSource(UserRealm userRealm) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager(userRealm));
        return aasa;
    }



}
