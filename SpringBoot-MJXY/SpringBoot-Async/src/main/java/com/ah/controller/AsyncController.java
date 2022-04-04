package com.ah.controller;

import com.ah.async.AsyncTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 5:29 PM
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncTest asyncTest;

    @RequestMapping("/asyncTest")
    public String asyncTest() {
        asyncTest.async();
        System.out.println("main:" + Thread.currentThread().getId() + ",名称：" + Thread.currentThread().getName());
        return "1";
    }
}
