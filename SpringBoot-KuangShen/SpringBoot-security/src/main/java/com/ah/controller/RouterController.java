package com.ah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/17 00:44
 */
@Controller
public class RouterController {
    @RequestMapping({"/", "/index"})
    public  String index() {
        return "index";
    }
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/views/login";
    }
    @RequestMapping("/level1/{id}")
    public String toLevel1(@PathVariable int id) {
        return String.format("/views/level1/%d", id);
    }
    @RequestMapping("/level2/{id}")
    public String toLevel2(@PathVariable int id) {
        return String.format("/views/level2/%d", id);
    }
    @RequestMapping("/level3/{id}")
    public String toLevel3(@PathVariable int id) {
        return String.format("/views/level3/%d", id);
    }
}
