package com.aking.learn._04_nio._03_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        // 设置非阻塞模式
        socketChannel.configureBlocking(false);

        // 提供服务器端的ip&端口
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        // 连接服务器
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        // 如果连接成功，发送数据
        String str = "hello, 大傻子";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        // 将buffer数据写入  channel
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
