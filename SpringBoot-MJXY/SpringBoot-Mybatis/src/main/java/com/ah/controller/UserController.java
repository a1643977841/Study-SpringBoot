package com.ah.controller;

import com.ah.entity.User;
import com.ah.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:20 PM
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserById")
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @RequestMapping("/addUser")
    public String addUser(String username, String sex) {
        userMapper.addUser(username, sex);
        return "添加用户：" + username + ",成功";
    }
}
