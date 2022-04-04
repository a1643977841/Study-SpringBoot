package com.ah.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/17 16:35
 */
@Controller
@Slf4j
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello");
        return "index";
    }

    @RequestMapping("/user/addUser")
    public String addUser(Model model) {
        return "/user/addUser";
    }

    @RequestMapping("/user/updateUser")
    public String updateUser(Model model) {
        return "/user/updateUser";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        // 获取shiro Subject
        Subject subject = SecurityUtils.getSubject();
        // username 和password生成token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登陆令牌 会走登陆认证 如果没有异常则登陆成功
        try{
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            // 该异常代表用户名不存在
            e.printStackTrace();
            model.addAttribute("msg","用户名不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            // 该异常代表密码错误
            e.printStackTrace();
            model.addAttribute("msg", "密码错误！");
            return "login";
        }
    }
    @RequestMapping("/logut")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
