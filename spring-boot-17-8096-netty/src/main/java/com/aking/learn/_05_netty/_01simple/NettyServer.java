package com.aking.learn._05_netty._01simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
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
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * ChannelHandler 是一个接口，处理 I/O 事件或拦截 I/O 操作，
                     * 并将其转发到其 ChannelPipeline(业务处理链)中的下一个处理程序。
                     * ChannelPipeline 是 保存 ChannelHandler 的 List，用于处理或拦截 Channel 的入栈事件和出栈操作
                     * @param ch
                     * @throws Exception
                     */
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        System.out.println("......server is ready");
        /**
         * 绑定一个端口并同步生成一个 channelFuture 对象
         */
        ChannelFuture cf = bootstrap.bind(6668).sync();
        // 给cf注册监听器，监控我们关心的事件
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("监听端口 6668 成功");
                } else {
                    System.out.println("监听端口 6668 失败");
                }
            }
        });
        /**
         * 对关闭通道进行监听
         */
        cf.channel().closeFuture().sync();

    }
}
