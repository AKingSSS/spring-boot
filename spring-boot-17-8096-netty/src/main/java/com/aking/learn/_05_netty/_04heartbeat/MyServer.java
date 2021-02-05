package com.aking.learn._05_netty._04heartbeat;

import com.aking.learn._05_netty._03chat.GroupChatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-05
 */
public class MyServer {
    public static void main(String[] args) throws Exception {
        // 创建两个线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //日志处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            /**
                             * IdleStateHandler:处理空闲状态的处理器
                             * long readerIdleTime: 表示多长时间没有读，就会发送一个心跳检测包检测是否连接
                             * long writerIdleTime：表示多长时间没有写，就会发送一个心跳检测包检测是否连接
                             * long allIdleTime:表示多长时间没有读写，就会发送一个心跳检测包检测是否连接
                             * 说明：
                             *  * Triggers an IdleStateEvent when a Channel has not performed
                             *  * read, write, or both operation for a while.
                             *  *  当IdleStateHandler触发，会传递给下一handler去处理，通过调用（触发）下一handler的userEventTriggered
                             */
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            // 加入一个空闲检测进一步处理handler（自定义）
                            pipeline.addLast(new MyServerHandler());
                        }
                    });
            System.out.println("netty 服务器启动");

            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();

            // 监听关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
