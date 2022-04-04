package com.ah.service;

import com.ah.entity.User;

import java.util.List;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/16 22:43
 */
public interface IUserService {

    /**
     * 查询所有用户
     * @return nick
     */
    List<User> queryUserList();

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return nick
     */
    User queryUserById(Integer id);

    /**
     * 新增一个用户
     * @param user user
     * @return 是否成功
     */
    int addUser(User user);

    /**
     * 修改一个用户
     * @param user user
     * @return 是否成功
     */
    int updateUser(User user);
    /**
     * 删除一个用户
     * @param id user
     * @return 是否成功
     */
    int deleteUser(Integer id);
}
