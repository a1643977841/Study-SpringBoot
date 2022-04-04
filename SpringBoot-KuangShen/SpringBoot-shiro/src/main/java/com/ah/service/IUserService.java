package com.ah.service;

import com.ah.entity.User;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1822:13
 */
public interface IUserService {
    /**
     * 根据用户名查询用户
     * @param username nick
     * @return nick
     */
    User queryUserByName(String username);
}
