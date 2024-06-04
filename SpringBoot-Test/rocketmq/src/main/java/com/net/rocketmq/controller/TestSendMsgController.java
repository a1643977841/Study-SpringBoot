package com.net.rocketmq.controller;

import com.net.rocketmq.producer.TestProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 17:04
 */
@RestController
@RequestMapping("/testSendMsg")
@Slf4j
@RequiredArgsConstructor
public class TestSendMsgController {

    private final TestProducer testProducer;

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam String topic, @RequestParam String tags, @RequestParam String keys, @RequestParam String message) {
        testProducer.sendMsg(topic, tags, keys, message);
        return "发送成功";
    }

}
