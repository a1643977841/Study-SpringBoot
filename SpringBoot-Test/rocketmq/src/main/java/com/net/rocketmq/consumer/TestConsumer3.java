package com.net.rocketmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 15:49
 */
@Component
@RocketMQMessageListener(consumerGroup = "test_group3", topic = "test_topic", selectorExpression = "test_tag3", messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.ORDERLY)
@RequiredArgsConstructor
@Slf4j
public class TestConsumer3 implements RocketMQListener<MessageExt> {
    @SneakyThrows
    @Override
    public void onMessage(MessageExt message) {
        String messageBody = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
        log.info("TestConsumer3 收到消息： messageId: {}, keys: {}, topic: {},tags: {},messageBody: {}", message.getMsgId(), message.getKeys(), message.getTopic(), message.getTags(), messageBody);
    }
}
