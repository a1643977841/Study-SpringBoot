package com.ah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 员工实体
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 3:04 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    private String lastName;

    private String email;
    /**
     * 0代表男
     * 1代表女
     */
    private Integer gender;

    private Department department;

    private Date birth;
}
