package com.net.rocketmq.service.impl;

import com.net.rocketmq.service.IRocketMqConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.stereotype.Service;

/**
 * @author Liu Hao
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RocketMqConsumerServiceImpl implements IRocketMqConsumerService {
    private String namesrvAddr;

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DefaultConsumer");




    @Override
    public void pushTest() {

    }

}
