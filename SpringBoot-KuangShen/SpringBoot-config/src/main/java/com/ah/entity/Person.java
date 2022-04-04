package com.ah.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 9:42 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@Validated // 数据校验
public class Person {

    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;
    @Email
    private String email;


}
