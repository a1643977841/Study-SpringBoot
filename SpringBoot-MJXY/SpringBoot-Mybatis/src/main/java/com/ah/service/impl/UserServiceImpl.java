package com.ah.service.impl;

import com.ah.mapper.UserMapper;
import com.ah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:49 PM
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper  userMapper;
    /**
     * 测试食物
     */
    @Override
    @Transactional
    public void tranfor() {
        // 更新数据
        userMapper.update("更新",3);
        // 异常
        System.out.println("修改成功，执行删除");
        // 删除一条数据
        userMapper.delete(2);
    }
}
