package com.ah.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 5:12 PM
 */
@Component
public class TimerTest {
    /**
     * 2000毫秒执行一次
     * 还可以使用cron 表达式
     */
    @Scheduled(fixedRate = 2000)
    public void showTimer() {
        System.out.println("定时任务开始，当前时间戳：" + System.currentTimeMillis());
    }
}
