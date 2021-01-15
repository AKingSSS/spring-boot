package com.aking.learn._01_simple.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Send Messages Asynchronously
 * </p>
 *
 * @author yk
 * @date 2021-01-12
 */
@Slf4j
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        // 1.用生产组名进行实例化
        DefaultMQProducer producer = new DefaultMQProducer("pt_h5");
        // 2.指明服务器地址
        producer.setNamesrvAddr("39.98.238.47:9876");
        // 3.启动实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            int index = i;
            // 4、实例消息
            Message msg = new Message("Jodie_topic_1023",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 5、异步发送
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    log.info("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    e.printStackTrace();
                    log.error("{} Exception {} ", index, e);
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 6、关闭
        producer.shutdown();
    }
}
