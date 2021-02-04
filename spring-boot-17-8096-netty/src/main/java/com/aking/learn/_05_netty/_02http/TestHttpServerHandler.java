package com.aking.learn._05_netty._02http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-02
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg是否为httpRequest请求
        if(msg instanceof HttpRequest) {
            System.out.println("msg 类型" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            // 获取 url
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                // 过滤
                System.out.println("请求了 favicon.ico，不做相应");
                return;
            }

            /**
             * 回复信息给浏览器【http协议】
             * Netty 提供一个专门用来操作缓冲区(即Netty的数据容器)的工具类 Unpooled
             */
            ByteBuf content = Unpooled.copiedBuffer("Hello , I AM AKING", CharsetUtil.UTF_8);

            // httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
