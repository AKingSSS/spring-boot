package com.aking.learn.listener;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-15
 */
@Component
@RocketMQMessageListener(topic = "springboot-rocketmq-topic", consumerGroup = "test-group", consumeMode = ConsumeMode.CONCURRENTLY)
public class RocketMqListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("msg = " + message);
    }
}
