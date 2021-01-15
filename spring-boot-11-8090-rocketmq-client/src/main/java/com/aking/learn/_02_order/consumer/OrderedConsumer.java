package com.aking.learn._02_order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * Subscription message sample code
 * </p>
 *
 * @author yk
 * @date 2021-01-13
 */
public class OrderedConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = null;
        try {
            // 1、使用指定的用户组名实例化
            consumer = new DefaultMQPushConsumer("OrderConsumer");
            // 2. 指定服务器地址
            consumer.setNamesrvAddr("39.98.238.47:9876");
            // 3、订阅消息
            consumer.subscribe("OrderTopic", "createTag || payTag || sendTag");
            // 4、 监听
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                           ConsumeOrderlyContext context) {
                    //模拟业务处理消息的时间
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                        msgs.forEach(msg -> {
                            System.out.println(new String(msg.getBody()));
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            // 5、开启
            consumer.start();
            System.out.printf("Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
