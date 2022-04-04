package com.ah.controller;

import com.ah.entity.User;
import com.ah.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/12 12:51 AM
 */
@RestController
public class MybatisPlusController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户
     * @param id
     * @return
     */
    @RequestMapping("/getUser")
    public Object getUser(Integer id) {
        return userService.selectById(id);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public Object deleteUser(Integer id) {
        return userService.deleteById(id);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectPage")
    public Object selectPage(Integer pageNum, Integer pageSize) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        Page<User> page = new Page<>((pageNum - 1) * pageSize, pageSize);
        return userService.selectPage(page, wrapper);
    }

    /**
     * 查询List
     * @return nick
     */
    @RequestMapping("/selectList")
    public Object selectList(String sex) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        // 查询男的
        wrapper.eq(User.SEX,sex);
        // false倒序
        wrapper.orderBy(User.ID,false);
        return userService.selectList(wrapper);
    }
}
