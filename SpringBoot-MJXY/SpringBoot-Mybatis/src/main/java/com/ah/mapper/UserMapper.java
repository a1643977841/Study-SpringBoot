package com.ah.mapper;

import com.ah.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 2:14 PM
 */
@Mapper
public interface UserMapper {
    /**
     * 获取用户
     * @param id id
     * @return nick
     */
    @Select("select * from user where id = #{id};")
    User getUserById(@Param("id") int id);

    /**
     * 新增用户
     * @param username 名称
     * @param sex 性别
     * @return 成功与否
     */
    @Insert("insert into user values(null,#{username},#{sex});")
    void addUser(@Param("username") String username,@Param("sex") String sex);

    /**
     * 更新用户
     * @param username
     * @param id
     * @return
     */
    @Update("update user set username=#{username} where id=#{id}")
    int update(@Param("username") String username,@Param("id") int id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Update("delete from user where id=#{id}")
    int delete(@Param("id") int id);

}

