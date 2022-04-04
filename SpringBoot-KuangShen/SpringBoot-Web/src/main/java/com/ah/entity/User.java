package com.ah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String name;

    private String username;

    private String password;

    private String age;
}
