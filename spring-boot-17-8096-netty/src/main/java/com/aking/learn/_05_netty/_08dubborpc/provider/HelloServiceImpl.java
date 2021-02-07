package com.aking.learn._05_netty._08dubborpc.provider;

import com.aking.learn._05_netty._08dubborpc.publicinterface.HelloService;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class HelloServiceImpl implements HelloService {
    /**
     * 当有消费方调用该方法时，返回一个结果
     *
     * @param msg
     * @return
     */
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息：" + msg);
        if (msg != null && msg != "") {
           return "你好，客户端，我已近收到你的消息：[" + msg + "]";
        } else {
            return "你好，客户端，我已近收到你的消息";
        }
    }
}
