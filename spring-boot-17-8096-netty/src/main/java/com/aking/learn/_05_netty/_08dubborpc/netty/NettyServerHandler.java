package com.aking.learn._05_netty._08dubborpc.netty;

import com.aking.learn._05_netty._08dubborpc.provider.HelloServiceImpl;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息，并调用服务
        System.out.println("msg = " + msg);
        //客户端调用服务的api，我们需要定义一个协议
        //比如我们需求，每次发送消息都必须以某个字段开头 “HelloService#hello”
        if (msg.toString().startsWith("HelloService#")) {
            String result =
                    new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
