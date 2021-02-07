package com.aking.learn._05_netty._07protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class MyMessageDecoder extends ReplayingDecoder<MessageProtocol> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode 被调用");
        // 将得到的二进制字节码转成 MessageProtocol
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        // 封装为MessageProtocol 对象，放入到 out，传递给下一个handler  业务处理
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setContent(content);
        messageProtocol.setLen(length);

        out.add(messageProtocol);
    }
}
