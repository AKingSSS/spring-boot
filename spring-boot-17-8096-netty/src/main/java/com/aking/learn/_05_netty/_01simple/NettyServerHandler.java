package com.aking.learn._05_netty._01simple;

import cn.hutool.core.net.multipart.UploadFile;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 自定义一个handler
 * </p>
 *
 * @author yk
 * @date 2021-01-29
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据（客户端发送的消息）
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
        System.out.println("client send msg = " + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("client address = " + ctx.channel().remoteAddress());


        /**
         * 异步执行
         */
        // 1.用户自定义的普通方法，提交到 taskQueue
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~two", CharsetUtil.UTF_8));

                } catch (Exception e) {
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });
        // 2.用户自定义定时任务,提交到 scheduledTaskQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~three", CharsetUtil.UTF_8));

                } catch (Exception e) {
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("go on....");
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入到缓存，并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~ one", CharsetUtil.UTF_8));
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
        ctx.close();
    }


}
