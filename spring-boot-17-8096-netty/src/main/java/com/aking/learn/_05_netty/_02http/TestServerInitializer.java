package com.aking.learn._05_netty._02http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-02
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();
        //加入netty,HttpServerCodec是处理http的编码解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //增加一个handler
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());
    }
}
