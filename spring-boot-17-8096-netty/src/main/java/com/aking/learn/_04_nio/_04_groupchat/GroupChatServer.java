package com.aking.learn._04_nio._04_groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @ClassName GroupChatServer
 * @Description 服务端
 * @Author aking
 * @Date 2021/1/24 21:30
 * @Version 1.0
 **/
public class GroupChatServer {
    /**
     * 定义属性
     */
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int port = 6667;

    /**
     * 构造器
     * 初始化工作
     */
    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();
            // ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(port));
            // 设置非阻塞模式
            listenChannel.configureBlocking(false);
            // 将 listenChannel 注册到 selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while (true) {
                // 阻塞
                int count = selector.select();
                // 有事件处理
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 如果监听到 accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线");
                        }
                        // 通道发送 read 事件
                        if (key.isReadable()) {
                            readData(key);
                        }
                        // 当前的 key 删除，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待...");
                }
            }
        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * 读取客户端消息
     */
    private void readData(SelectionKey key) {
        // 定义一个 socketChannel
        SocketChannel channel = null;
        try {
            // 得到 channel
            channel = (SocketChannel) key.channel();
            // 创建 buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 将 Channel 的数据读入缓冲区，返回读入到缓冲区的字节数
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                // 输出该消息
                System.out.println("from 客户端：" + msg.trim());
                // 向其他用户转发消息
                sendInfoToOtherClient(msg, channel);
            }
        } catch (Exception e1) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                key.channel();
                // 关闭通道
                channel.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其它客户（通道）
     * @param msg
     * @param self
     */
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws Exception {
        System.out.println("服务器转发消息...");
        // 遍历所有注册到selector上的socketChannel，并排除 self
        for (SelectionKey key : selector.keys()) {
            // 通过 key 取出对应的 channel
            Channel targetChannel = key.channel();
            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                // 将 msg 存储到 buffer
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                // 将 buffer 的数据写入到通道
                dest.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        final GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
