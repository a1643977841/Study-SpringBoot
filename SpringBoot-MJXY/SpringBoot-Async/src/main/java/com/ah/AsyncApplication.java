package com.ah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 5:33 PM
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }
}
