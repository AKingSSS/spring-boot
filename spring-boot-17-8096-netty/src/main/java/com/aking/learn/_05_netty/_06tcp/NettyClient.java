package com.aking.learn._05_netty._06tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p>
 * 客户端
 * </p>
 *
 * @author yk
 * @date 2021-01-29
 */
public class NettyClient {
    public static void main(String[] args) {
        // 客户端需要一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            // 设置相关参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());
            System.out.println("..... client ok");


            // 启动客户端去连接服务器
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668).sync();
            // 给关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
