package com.ah.test;

import com.ah.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/12 11:38 PM
 */
@Slf4j
public class LombokTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("ahao");
        user.setSex("22");
        log.info("user信息:{}",user.toString());
    }
}
