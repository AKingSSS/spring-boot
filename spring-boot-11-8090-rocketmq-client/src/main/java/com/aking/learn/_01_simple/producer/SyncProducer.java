package com.aking.learn._01_simple.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 *   Send Messages Synchronously
 * </p>
 *
 * @author yk
 * @date 2021-01-12
 */
public class SyncProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            // 1.用生产组名进行实例化
            producer = new DefaultMQProducer("pt_h5");
            // 2.指明服务器地址
            producer.setNamesrvAddr("39.98.238.47:9876");
            // 3.启动实例
            producer.start();
            // 4.创建一个消息实例，指定主题、标记和消息体
            for (int i = 0; i < 10; i++) {
                Message msg = new Message("TopicTest",
                        "TagA",
                        String.format("%s", "hello rocketmq" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.使用完关闭生产者实例
            producer.shutdown();
        }

    }
}
