package com.ah.controller;

import com.ah.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1923:03
 */
@Api(tags = "Swagger测试controller")
@RestController
public class HelloController {
    @ApiOperation("hello测试controller")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/queryUserList")
    public User queryUserList() {
        return new User();
    }
}
