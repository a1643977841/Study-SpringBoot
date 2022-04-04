package com.ah.mapper;

import com.ah.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/1821:57
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名称获取用户
     * @param username 用户名称
     * @return nick
     */
    User queryUserByName(String username);



}
