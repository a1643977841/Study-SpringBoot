package com.ah.entity;

import lombok.Data;

/**
 * lombok自动生成get set 构造方法
 * 可以单独 @Getter @Setter @AllArgsConstructor
 * 也可以直接@Data注解
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/12 11:36 PM
 */
@Data
public class User {
    private String username;
    private String sex;
}
