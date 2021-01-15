package com.aking.learn;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-15
 */
@SpringBootTest
@Slf4j
public class LearnApplication8091Test {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void test() {
        rocketMQTemplate.convertAndSend("springboot-rocketmq-topic", "hello springboot-message");
        log.info("消息发送成功");
    }
}
