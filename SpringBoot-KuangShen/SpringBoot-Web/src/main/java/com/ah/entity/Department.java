package com.ah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门实体
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/15 2:58 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Integer id;

    private String name;
}
