package com.aking.learn._05_netty._07protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-06
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 接收到数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端接收到信息如下：");
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));

        System.out.println("服务器接收到消息包数量：" + (++this.count) + "\r\n");
        // 回复消息
        String responseContent = UUID.randomUUID().toString();
        byte[] responseContentBytes = responseContent.getBytes(CharsetUtil.UTF_8);
        int  responseLen = responseContentBytes.length;

        // 构建协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContentBytes);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
