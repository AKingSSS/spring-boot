package com.aking.learn._05_batch.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  批量发送
 *  消息总大小 <= 1MB
 * </p>
 *
 * @author yk
 * @date 2021-01-14
 */
public class BatchProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            // Instantiate a producer to send scheduled messages
            producer = new DefaultMQProducer("ExampleProducerGroup");
            // 指定服务
            producer.setNamesrvAddr("39.98.238.47:9876");
            // Launch producer
            producer.start();
            String topic = "BatchTest";
            List<Message> messages = new ArrayList<>();
            messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID004", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID005", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID006", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID007", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID008", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID009", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID010", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID011", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID012", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID013", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID014", "Hello world 2".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID015", "Hello world 2".getBytes()));
            ListSplitter splitter = new ListSplitter(messages);
            while (splitter.hasNext()) {
                try {
                    List<Message> listItem = splitter.next();
                    producer.send(listItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    //handle the error
                }
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
