package com.net.rocketmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author LiuHao
 * @version V1.0
 * @date 2024/6/3 16:25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TestConsumer2 implements MessageListenerConcurrently {
    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_group2");


    @PostConstruct
    public void start() {
        try {
            consumer.setNamesrvAddr(namesrvAddr);

            // 从消息队列头部开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            // 设置集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            // 最大消费者线程数
            consumer.setConsumeThreadMax(30);
            // 最小消费者线程数
            consumer.setConsumeThreadMin(20);
            // 订阅主题
            consumer.subscribe("test_topic", "test_tag2");

            // 注册消息监听器
            consumer.registerMessageListener(this);

            // 启动消费端
            consumer.start();

            log.info("Message Consumer Start...");
            System.err.println("Message Consumer Start...");
        } catch (MQClientException e) {
            log.error("Message Consumer Start Error!!",e);
        }

    }


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        MessageExt message = msgs.get(0);
        try {
            //逐条消费
            String messageBody = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
            log.info("TestConsumer2 收到消息：messageId: {}, keys: {}, topic: {},tags: {},messageBody: {}", message.getMsgId(), message.getKeys(), message.getTopic(), message.getTags(), messageBody);

            //模拟业务异常
            // int i = 1 / 0;
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            log.error("Consume Message Error!!", e);
            //抛出异常时，返回ConsumeConcurrentlyStatus.RECONSUME_LATER，尝试重试。当重试指定次数后返回ConsumeConcurrentlyStatus.CONSUME_SUCCESS
            int reconsumeTimes = message.getReconsumeTimes();
            log.error("Now Retry Times: " + reconsumeTimes);
            if (reconsumeTimes >= 4) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
