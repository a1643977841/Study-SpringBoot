package com.ah.controller;

import com.ah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试事务
 * 在方法上添加@Transactional注解即开启事务
 * 特性：
 * 原子性：多个sql语句，那么都执行成功，要么都失败
 * 一致性：事务完成，必须使所有的数据保持一致
 * 隔离性：指当前事务的修改必须与其他事务隔离开（不能同时进行操作同一个数据）
 * 持久性：事务执行完毕之后，对数据的影响是永久的
 *
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 3:06 PM
 */
@RestController
public class TransactionalController {
    @Autowired
    private UserService userService;

    /**
     * 测试事务
     *
     * @return nick
     */
    @RequestMapping("/update")
    public String update() {
        userService.tranfor();
        return "更新成功";
    }
}
