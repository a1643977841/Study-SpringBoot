package com.ah.service.impl;

import com.ah.service.IRoleService;
import com.ah.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/17 10:06
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IRoleService roleService;


    @Override
    public int addUser(String user) {
        return 0;
    }
}
