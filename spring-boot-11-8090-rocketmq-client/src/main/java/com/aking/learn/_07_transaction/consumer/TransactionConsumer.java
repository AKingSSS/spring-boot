package com.aking.learn._07_transaction.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-15
 */
@Slf4j
public class TransactionConsumer {
    public static void main(String[] args) {
        try {
            // 1、使用指定的用户组名实例化
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pt_h5");
            // 2.指明服务器地址
            consumer.setNamesrvAddr("39.98.238.47:9876");
            // 3.订阅消息
            consumer.subscribe("TransactionTopic","TagA || TagB || TagC");
            // 4、监听
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {

                msgs.forEach(msg->{
                    byte[] body = msg.getBody();
                    log.info("{} Receive New Messages: {}", Thread.currentThread().getName(), new String(body));
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            // 5、开始
            consumer.start();
            log.info("Consumer Started");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
