package com.aking.learn._05_netty._08dubborpc.netty;

import com.aking.learn._05_netty._07protocoltcp.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class NettyClient {
    /**
     * 创建线程池
     */
    private static ExecutorService executor =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 10,
                    5, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100));

    private static NettyClientHandler client;

    /**
     * 获取代理对象
     * @param serviceClass
     * @param providerName
     * @return
     */
    public Object getBean(final Class<?> serviceClass, final String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass}, (proxy,method,args) -> {
                    if (client == null) {
                        initClient();
                    }
                    client.setParam(providerName + args[0]);
                    return executor.submit(client).get();
                });
    }


    /**
     * 初始化客户端
     */
    private static void initClient() {
        client = new NettyClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        // 设置相关参数
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(client);
                    }
                });
        System.out.println("..... client ok");

        try {
            bootstrap.connect("127.0.0.1", 7000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
