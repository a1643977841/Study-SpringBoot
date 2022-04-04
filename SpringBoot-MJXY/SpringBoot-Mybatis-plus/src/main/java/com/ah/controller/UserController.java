package com.ah.controller;

import com.ah.entity.User;
import com.ah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 4:53 PM
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUserById")
    public User getUserById(int id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/update")
    public Integer update(String username,int id) {
        return userService.update(username,id);
    }

    @RequestMapping("/addUser")
    public Integer addUser(String username,String sex) {
        return userService.addUser(username,sex);
    }
}
