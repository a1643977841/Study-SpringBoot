package com.ah.mapper;

import com.ah.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/16 18:18
 */
@Mapper
public interface UserMapper {
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
     * @param id userId
     * @return 是否成功
     */
    int deleteUser(Integer id);
}
