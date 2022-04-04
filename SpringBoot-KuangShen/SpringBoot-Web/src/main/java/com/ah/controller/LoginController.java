package com.ah.controller;

import com.ah.entity.User;
import com.ah.mock.UserInfoMock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 22:32
 */
@Controller
public class LoginController {
    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @param model nick
     * @return nick
     */
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            model.addAttribute("msg","请输入用户名或密码！");
            model.addAttribute("success",false);
            return "login1.html";
        } else {
            Collection<User> userInfos = UserInfoMock.userInfos.values();
            if(userInfos.size() > 0) {
                for(User user : userInfos) {
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        model.addAttribute("msg","登陆成功！");
                        model.addAttribute("userInfo",user);
                        model.addAttribute("success",true);
                        return "index.html";
                    }
                }
            }
        }
        model.addAttribute("msg","用户名或者密码输入错误！");
        model.addAttribute("success",false);
        return "login1.html";
    }
}
