package com.aking.learn._05_netty._01simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * <p>
 * 自定义一个handler
 * </p>
 *
 * @author yk
 * @date 2021-01-29
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx = " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server: 五哈", CharsetUtil.UTF_8));
    }

    /**
     * 当通道有读取事件触发
     *
     * @param ctx 上下文对象，含有管道 pipLine，通道 channel，地址
     * @param msg 发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        // 将 msg 转成 ByteBuf
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("server send msg = " + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("server address = " + ctx.channel().remoteAddress());
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
