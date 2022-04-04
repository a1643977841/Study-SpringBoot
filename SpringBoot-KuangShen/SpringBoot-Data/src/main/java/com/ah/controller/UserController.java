package com.ah.controller;

import com.ah.entity.User;
import com.ah.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/16 23:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        return userService.queryUserList();
    }
    @GetMapping("/queryUserById")
    public User queryUserById(Integer id) {
        return userService.queryUserById(id);
    }

    @PostMapping("/addUser")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PostMapping("/updateUser")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @DeleteMapping("/deleteUser")
    public int deleteUser(Integer id) {
        return userService.deleteUser(id);
    }

}
