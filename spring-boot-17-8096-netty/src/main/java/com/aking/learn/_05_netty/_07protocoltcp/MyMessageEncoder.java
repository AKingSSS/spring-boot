package com.aking.learn._05_netty._07protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
