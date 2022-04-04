package com.ah.controller;

import com.ah.entity.User;
import com.ah.mapper.test1.UserMapper1;
import com.ah.mapper.test2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源测试
 * 在实际情况中 这种配置是影响效率的
 * 一般是分布式，微服务 在分布式和微服务中，一般是提供api调用
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 3:54 PM
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper1 UserMapper1;
    @Autowired
    private UserMapper2 UserMapper2;

    /**
     * 获取数据库1
     * @param id
     * @return
     */
    @GetMapping("/getUserById1")
    public User getUserById1(int id) {

        return UserMapper1.getUserById(id);
    }

    /**
     * 获取数据库2
     * @param id
     * @return
     */
    @GetMapping("/getUserById2")
    public User getUserById2(int id) {

        return UserMapper2.getUserById(id);
    }

}
