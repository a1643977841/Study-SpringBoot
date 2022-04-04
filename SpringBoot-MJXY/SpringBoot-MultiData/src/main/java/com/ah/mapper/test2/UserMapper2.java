package com.ah.mapper.test2;

import com.ah.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 数据源2
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 3:53 PM
 */
@Mapper
public interface UserMapper2 {
    /**
     * 获取用户
     *
     * @param id id
     * @return nick
     */
    @Select("select * from user where id = #{id};")
    User getUserById(@Param("id") int id);
}
