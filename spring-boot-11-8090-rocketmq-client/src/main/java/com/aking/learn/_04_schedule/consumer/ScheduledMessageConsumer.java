package com.aking.learn._04_schedule.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
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
 * @date 2021-01-13
 */
public class ScheduledMessageConsumer {
    public static void main(String[] args) {
        try {
            // 1、 Instantiate message consumer
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
            // 2、 指定服务
            consumer.setNamesrvAddr("39.98.238.47:9876");
            // 3、Subscribe topics
            consumer.subscribe("ScheduledTopic", "*");
            // 4、Register message listener
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                    for (MessageExt message : messages) {
                        // Print approximate delay time period
                        System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
                                + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            // 5、Launch consumer
            consumer.start();
            System.out.printf("Scheduled Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
