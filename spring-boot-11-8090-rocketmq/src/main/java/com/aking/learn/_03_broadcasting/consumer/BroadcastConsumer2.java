package com.aking.learn._03_broadcasting.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-13
 */
public class BroadcastConsumer2 {
    public static void main(String[] args) {
        try {
            // 1、以组名实例化 consumer
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
            // 2、指定服务地址
            consumer.setNamesrvAddr("39.98.238.47:9876");
            // 3、广播模式
            consumer.setMessageModel(MessageModel.BROADCASTING);
            // 4、订阅消息
            consumer.subscribe("TopicBroadcast", "TagA || TagC || TagD");
            // 5、监听
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            // 6、开启
            consumer.start();
            System.out.printf("Broadcast Consumer Started.%n");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
