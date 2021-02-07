package com.aking.learn._05_netty._08dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private String result;
    /**
     * 客户端调用方法时传递参数
     */
    private String param;

    /**
     * 服务器创建成功后，调用
     * 第一个被调用的方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;

    }

    /**
     * 收到服务器数据后，调用方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        // 唤醒等待的线程
        notify();
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     * 被代理对象调用，发送数据给服务器 -> wait ->等待被唤醒 ->返回结果
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        // 进行wait，等待channelRead获取服务器的结果,唤醒
        wait();
        return result;
    }

    void setParam(String param) {
        this.param = param;
    }
}
