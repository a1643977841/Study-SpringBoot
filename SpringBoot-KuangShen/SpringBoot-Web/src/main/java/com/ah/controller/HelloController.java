package com.ah.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 11:27 PM
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("toHello")
    public String toHello() {
        return "Hello!";
    }
}
