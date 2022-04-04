package com.ah.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 全局日志配置
 * 使用aop
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 3:01 AM
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.ah.controller..*.*(..))")
    public void webLog() {

    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求,记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        String url = request.getRequestURI().toString();
        log.info("URL: {}",url);
        String method = request.getMethod();
        log.info("Method: {}",method);
        String remoteAddr = request.getRemoteAddr();
        log.info("IP: {}", remoteAddr);
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            log.info("name:{},value:{}",name,request.getParameter(name));
        }
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("Response: {}", ret);
    }

}
