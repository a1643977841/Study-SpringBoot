package com.ah.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 9:41 PM
 */
@Data
@Component
public class Dog {

    private Integer age;

    private String name;

}
