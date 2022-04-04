package com.ah.service;


import com.ah.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Service;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:49 PM
 */
@Service
public interface UserService extends IService<User> {

    /**
     * 获取用户
     * @param id
     */
    User getUserById(int id);

    /**
     * 更新数据
     * @param username
     * @param id
     * @return
     */
    Integer update(String username,int id);

    /**
     * 新增用户
     * @param username
     * @param sex
     * @return
     */
    Integer addUser(String username,String sex);
}
