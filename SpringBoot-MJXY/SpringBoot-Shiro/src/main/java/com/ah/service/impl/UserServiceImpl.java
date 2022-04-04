package com.ah.service.impl;

import com.ah.entity.User;
import com.ah.mapper.UserMapper;
import com.ah.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:49 PM
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public Integer update(String username, int id) {
        User user = new User();
        user.setUsername(username);
        user.setId(id);
        Integer integer = userMapper.updateById(user);
        return integer;
    }
    @Override
    public Integer addUser(String username,String sex) {
        User user = new User();
        user.setUsername(username);
        user.setSex(sex);
        Integer integer = userMapper.insert(user);
        return integer;
    }


}
