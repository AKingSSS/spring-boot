package com.aking.learn._04_schedule.provider;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-13
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            // Instantiate a producer to send scheduled messages
            producer = new DefaultMQProducer("ExampleProducerGroup");
            // 指定服务
            producer.setNamesrvAddr("39.98.238.47:9876");
            // Launch producer
            producer.start();
            int totalMessagesToSend = 100;
            for (int i = 0; i < totalMessagesToSend; i++) {
                Message message = new Message("ScheduledTopic", ("Hello scheduled message " + i).getBytes());
                // This message will be delivered to consumer 10 seconds later.
                message.setDelayTimeLevel(3);
                // Send the message
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown producer after use.
            producer.shutdown();
        }
    }
}
