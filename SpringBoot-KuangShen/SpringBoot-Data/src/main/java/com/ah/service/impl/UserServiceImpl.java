package com.ah.service.impl;

import com.ah.entity.User;
import com.ah.mapper.UserMapper;
import com.ah.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/16 22:44
 */
@Service
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }
}
