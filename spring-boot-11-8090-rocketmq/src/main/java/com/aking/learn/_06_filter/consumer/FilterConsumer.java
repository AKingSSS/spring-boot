package com.aking.learn._06_filter.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
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
 * @date 2021-01-14
 */
public class FilterConsumer {
    public static void main(String[] args) {
        try {
            // 1、 Instantiate message consumer
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
            // 2、 指定服务
            consumer.setNamesrvAddr("39.98.238.47:9876");
            // 3、Subscribe topics
            consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    msgs.forEach(msg -> {
                        System.out.println(new String(msg.getBody()));
                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.printf("Filter Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
