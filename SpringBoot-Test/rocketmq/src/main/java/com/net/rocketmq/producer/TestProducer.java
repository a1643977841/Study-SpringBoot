package com.net.rocketmq.producer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 17:01
 */
@Component
@Slf4j
public class TestProducer {
    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    DefaultMQProducer producer = new DefaultMQProducer("DefaultProducer");

    @PostConstruct
    public void start() {
        try {
            producer.setNamesrvAddr(namesrvAddr);

            // 设置内部重试的次数，可能导致消息重复发送。
            producer.setRetryTimesWhenSendFailed(5);
            producer.start();

            log.info("Message Product Start...");
            System.err.println("Message Product Start...");
        } catch (MQClientException e) {
            log.error("Message Product Start Error!!", e);
        }
    }


    @SneakyThrows
    public void sendMsg(String topic, String tags, String keys, String message) {
        try {
            for (int i = 0; i < 10; i++) {
                Message msg = new Message(topic, tags, keys, (message + "_" + i).getBytes());
                SendResult sendResult = producer.send(msg);
                if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                    log.info("消息发送成功");
                } else {
                    log.error("消息发送失败");
                }
            }
        } finally {
            // producer.shutdown();
        }
    }
}
