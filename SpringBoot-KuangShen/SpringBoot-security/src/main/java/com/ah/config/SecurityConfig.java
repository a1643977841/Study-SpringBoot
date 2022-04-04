package com.ah.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/17 13:23
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问 功能也只有对应有权限的人可以访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        // 没有权限默认跳登陆页, 需要开启
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");
        // 开启注销功能，跳转到首页
        http.logout().logoutSuccessUrl("/");
        // 关闭csrf
        http.csrf().disable();
        // 开启记住我 默认保存两周
        http.rememberMe()
                // 自定义接受参数名称
                .rememberMeParameter("remember");

    }

    /**
     * 认证
     * @param auth nick
     * @throws Exception nick
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置用户需要配置密码加密器 否则会报错 There is no PasswordEncoder mapped for the id "null"
        auth.inMemoryAuthentication()
                .withUser("ahao").password(passwordEncoder.encode("123456")).roles("vip1","vip2")
                .and()
                .withUser("ahaos").password(passwordEncoder.encode("123456")).roles("vip3")
                .and()
                .withUser("root").password(passwordEncoder.encode("123456")).roles("vip1","vip2","vip3");
    }
}
