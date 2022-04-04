package com.ah.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 23:19
 */
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户session
        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            request.setAttribute("msg","请先登陆！");
            request.getRequestDispatcher("/login1.html").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
