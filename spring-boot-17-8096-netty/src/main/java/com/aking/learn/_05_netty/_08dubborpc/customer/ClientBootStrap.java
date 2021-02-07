package com.aking.learn._05_netty._08dubborpc.customer;

import com.aking.learn._05_netty._08dubborpc.netty.NettyClient;
import com.aking.learn._05_netty._08dubborpc.publicinterface.HelloService;

import javax.sql.rowset.serial.SerialArray;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class ClientBootStrap {

    private static final String providerName = "HelloService#hello#";

    public static void main(String[] args) {
        NettyClient customer = new NettyClient();

        HelloService helloService = (HelloService) customer.getBean(HelloService.class, providerName);

        String result = helloService.hello("你好， dubbo~");
        System.out.println("调用的结果 res = " + result);
    }
}
