package com.ah.backend.config;

import cn.hutool.json.JSONUtil;
import com.ah.backend.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2023/5/9 22:24
 */
@Configuration
@EnableWebSecurity

public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests() // 重定向验证
                .anyRequest().authenticated() // 所有请求都要去验证
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .and()
                .csrf()
                .disable()
                .build();
    }

    /**
     *  登录成功处理器
     * @param request 请求
     * @param response 响应
     * @param authentication 认证
     * @throws IOException 异常
     */
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(R.ok()));
    }

    /**
     * 登录失败处理器
     * @param request 请求
     * @param response 响应
     * @param exception 异常
     * @throws IOException 异常
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                 HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("登录失败");
    }
}
