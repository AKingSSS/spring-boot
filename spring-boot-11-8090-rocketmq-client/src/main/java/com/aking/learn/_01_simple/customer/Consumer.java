package com.aking.learn._01_simple.customer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-12
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 1、使用指定的用户组名实例化
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pt_h5");
        // 2.指明服务器地址
        consumer.setNamesrvAddr("39.98.238.47:9876");
        // 3.订阅消息
        consumer.subscribe("TopicTest","*");
        // 4、监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                msgs.forEach(msg->{
                    byte[] body = msg.getBody();
                    log.info("{} Receive New Messages: {}", Thread.currentThread().getName(), new String(body));
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 5、开始
        consumer.start();
        log.info("Consumer Started");
    }
}
