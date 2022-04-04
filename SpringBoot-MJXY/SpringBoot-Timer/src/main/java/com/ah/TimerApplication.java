package com.ah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 5:14 PM
 */
@SpringBootApplication
@EnableScheduling
public class TimerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimerApplication.class, args);
    }
}
