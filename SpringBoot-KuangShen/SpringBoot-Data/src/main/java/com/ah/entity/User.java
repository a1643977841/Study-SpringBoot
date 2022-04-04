package com.ah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/16 18:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String username;

    private String password;

    private Integer roleId;
}
