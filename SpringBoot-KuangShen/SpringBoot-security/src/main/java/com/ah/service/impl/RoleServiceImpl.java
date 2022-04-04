package com.ah.service.impl;

import com.ah.service.IRoleService;
import com.ah.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/17 10:07
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IUserService userService;

    @Override
    public int addRole(String role) {
        return 0;
    }
}
