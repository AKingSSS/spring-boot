package com.aking.learn._07_transaction.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-15
 */
public class TransactionProvider {
    public static void main(String[] args) {
        TransactionMQProducer producer = null;
        try {
            producer = new TransactionMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr("39.98.238.47:9876");
            producer.setTransactionListener(new TransactionListener() {
                /**
                 * 发送半事务消息成功后执行本地事务
                 * @param msg
                 * @param arg
                 * @return
                 */
                @Override
                public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                    if (StringUtils.equals("TagA", msg.getTags())) {
                        // 提交事务，允许订阅方消费该消息
                        return LocalTransactionState.COMMIT_MESSAGE;
                    } else if (StringUtils.equals("TagB", msg.getTags())) {
                        // 回滚事务，消息将被丢弃不允许消费
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    } else {
                        // 暂时无法判断状态，等待固定时间以后消息队列RocketMQ版服务端向发送方进行【消息回查】。
                        return LocalTransactionState.UNKNOW;
                    }
                }

                @Override
                public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                    // 当状态是 UNKNOWN 时， 进行回查
                    System.out.println("tag = " + msg.getTags());
                    // 事务重新提交
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
            });
            producer.start();
            String[] tags = new String[]{"TagA", "TagB", "TagC"};
            for (int i = 0; i < 3; i++) {
                Message msg =
                        new Message("TransactionTopic", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + tags[i % tags.length]).getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送半事务消息
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);
            }
            for (int i = 0; i < 100000; i++) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
