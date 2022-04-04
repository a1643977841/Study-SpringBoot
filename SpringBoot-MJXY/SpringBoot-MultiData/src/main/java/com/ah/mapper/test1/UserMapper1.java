package com.ah.mapper.test1;

import com.ah.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 数据源1
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/11 3:52 PM
 */
@Mapper
public interface UserMapper1 {
    /**
     * 获取用户
     * @param id id
     * @return nick
     */
    @Select("select * from user where id = #{id};")
    User getUserById(@Param("id") int id);
}
