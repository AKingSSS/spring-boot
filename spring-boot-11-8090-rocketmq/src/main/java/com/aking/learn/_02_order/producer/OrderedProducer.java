package com.aking.learn._02_order.producer;

import com.google.gson.Gson;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * <p>
 * 场景：如果一个用户完成一个订单需要3条消息，
 * 比如订单的创建、订单的支付、订单的发货，
 * 很显然，同一个用户的订单消息必须要顺序消费，但是不同用户之间的订单可以并行消费
 * </p>
 *
 * @author yk
 * @date 2021-01-13
 */
public class OrderedProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = null;
        try {
            producer = new DefaultMQProducer("OrderProducer");
            producer.setNamesrvAddr("39.98.238.47:9876");
            producer.start();

            String[] tags = new String[]{"createTag", "payTag", "sendTag"};

            // 订单消息
            for (int orderId = 0; orderId <= 100; orderId++) {
                for (int type = 0; type < tags.length; type++) {
                    // 消息体(同一个订单的消息)
                    Message msg = new Message("OrderTopic",
                            tags[type],
                            orderId + ":" + type, (orderId + ":" + type).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    // 发送消息（重点）
                    producer.send(msg, new MessageQueueSelector() {
                        /**
                         *
                         * @param mqs:消息要发送的Topic下所有的分区
                         * @param message:消息对象
                         * @param arg：额外参数
                         * @return
                         */
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                            // 保证相同的订单的消息被路由到相同的分区
                            System.out.println("mqs = " + new Gson().toJson(mqs));
                            Integer id = (Integer) arg;
                            System.out.println("id = " + id);
                            int index = id % mqs.size();
                            return mqs.get(index);
                        }
                    }, orderId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
