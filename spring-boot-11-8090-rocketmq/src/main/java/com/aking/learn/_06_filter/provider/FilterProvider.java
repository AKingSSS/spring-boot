package com.aking.learn._06_filter.provider;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-14
 */
public class FilterProvider {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr("39.98.238.47:9876");
            producer.start();

            for (int i = 0; i < 10; i++) {
                Message msg = new Message("TopicTest",
                        "TAGA",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                // Set some properties.
                msg.putUserProperty("a", String.valueOf(i));

                SendResult sendResult = producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
