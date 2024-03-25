package com.net.mybatis.controller;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/1/22 16:54
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/testSession")
public class TestSessionController {

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("username", "张三");
        return "登录成功";
    }


    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request) {
        return ServletUtil.getClientIP(request);
    }
}
