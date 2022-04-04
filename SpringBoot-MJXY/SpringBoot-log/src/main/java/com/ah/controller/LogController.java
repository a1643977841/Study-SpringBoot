package com.ah.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 3:20 AM
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @RequestMapping("/toHello")
    public String toHello() {
        return "Hello";
    }
}
