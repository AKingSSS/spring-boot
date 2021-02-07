package com.aking.learn._05_netty._08dubborpc.provider;

import com.aking.learn._05_netty._08dubborpc.netty.NettyServer;

/**
 * <p>
 *  服务的提供者
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
