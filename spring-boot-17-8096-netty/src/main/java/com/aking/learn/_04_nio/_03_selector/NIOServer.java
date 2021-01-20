package com.aking.learn._04_nio._03_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建 serverSocketChannel -> serverSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 获取 selector 实例
        Selector selector = Selector.open();

        // 绑定端口 6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel注册到selector，关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后 socketChannel 数量 = " + selector.keys().size());

        // 循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了 1 s，无连接");
                continue;
            }
            // 如果返回大于 0，获取相关的 SelectionKey 集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 通过selectionKeys反向获取通道，迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 根据 key 的状态做出对应的处理
                if(key.isAcceptable()) {
                    //关心事件为 OP_ACCEPT,有新的客户端连接（事件驱动）
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 将socketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);

                    System.out.println("客户端连接成功， 生成了一个 socketChannel： " + socketChannel.hashCode());

                    // 将socketChannel注册到selector，关注事件为 OP_READ，同时给 socketchannel 关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后，注册的 socketChannel 数量 = " + selector.keys().size());
                }

                if (key.isReadable()) {
                    // 通过key 反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

                    channel.read(byteBuffer);
                    System.out.println("from 客户端 " + new String(byteBuffer.array()));

                }

                // 手动从集合中移除当前的 selectionKey，防止重复操作
                keyIterator.remove();
            }

        }




    }
}
