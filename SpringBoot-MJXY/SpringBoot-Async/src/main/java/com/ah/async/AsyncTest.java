package com.ah.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author LiuHao
 * @version V1.0
 * @date 2022/3/13 5:29 PM
 */
@Component
public class AsyncTest {
    /**
     * 标注这是一个异步方法
     */
    @Async
    public void async() {
        System.out.println("异步方法，当前线程id：" + Thread.currentThread().getId() + "，名称：" + Thread.currentThread().getName());
    }
}
