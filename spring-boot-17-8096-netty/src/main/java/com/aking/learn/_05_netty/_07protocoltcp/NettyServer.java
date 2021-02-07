package com.aking.learn._05_netty._07protocoltcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>
 * 服务端
 * </p>
 *
 * @author yk
 * @date 2021-01-29
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 创建 BossGroup  和 workerGroup
         * 说明：
         * 1、创建 两个线程组 BossGroup、workerGroup
         * 2、bossGroup 只处理连接请求，真正和客户端业务处理，会交给 workerGroup 完成
         * 3、两个都是无限循环
         */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        /**
         * 创建服务器端的启动对象，配置参数
         * 服务端启动引导类
         */
        ServerBootstrap bootstrap = new ServerBootstrap();

        /**
         * 链式编程设置:
         * 设置 2 个线程组
         * 使用 NioServerSocketChannel 作为服务器的通道实现
         * 设置线程队列得到连接数
         * 设置保持活动连接状态
         * 设置处理器
         */
        bootstrap.group(bossGroup, workerGroup)
                // 设置服务器通道实现
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                // 一直保持连接活动状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new MyServerInitializer());
        System.out.println("......server is ready");
        /**
         * 绑定一个端口并同步生成一个 channelFuture 对象
         */
        ChannelFuture cf = bootstrap.bind(6668).sync();
        /**
         * 对关闭通道进行监听
         */
        cf.channel().closeFuture().sync();
    }
}
