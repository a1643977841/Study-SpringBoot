package com.ah.service.impl;

import com.ah.entity.User;
import com.ah.mapper.UserMapper;
import com.ah.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1822:14
 */
@Service
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名称获取用户
     * @param username nick
     * @return User
     */
    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
