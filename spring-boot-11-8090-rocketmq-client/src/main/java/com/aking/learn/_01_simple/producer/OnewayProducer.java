package com.aking.learn._01_simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * <p>
 * Send Messages in One-way Mode
 * </p>
 *
 * @author yk
 * @date 2021-01-12
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        // 1.用生产组名进行实例化
        DefaultMQProducer producer = new DefaultMQProducer("pt_h5");
        // 2.指明服务器地址
        producer.setNamesrvAddr("39.98.238.47:9876");
        // 3.启动实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" ,
                    "TagA" ,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        //Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
    }

}
