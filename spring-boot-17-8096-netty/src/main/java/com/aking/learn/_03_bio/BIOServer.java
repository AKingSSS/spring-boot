package com.aking.learn._03_bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10,
                1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

        // 创建 serversocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动~~");

        while (true) {
            // 打印线程信息
            System.out.println("①线程信息 id = " + Thread.currentThread().getId() +
                    ";名字 = " + Thread.currentThread().getName());
            // 监听，等待客户端连接(阻塞)
            System.out.println("等待客户端连接...");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            // 打印线程信息
            System.out.println("②线程信息 id = " + Thread.currentThread().getId() +
                    ";名字 = " + Thread.currentThread().getName());
            // 创建一个线程与之通讯
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 可以和客户端通讯
                    handle(socket);
                }
            });
        }
    }

    /**
     * 编写一个handle方法，和客户端通讯
     *
     * @param socket
     */
    public static void handle(Socket socket) {
        try {
            // 打印线程信息
            System.out.println("③线程信息 id = " + Thread.currentThread().getId() +
                    ";名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过 socket 获取输入流
            InputStream inputStream = socket.getInputStream();

            while (true) {
                // 打印线程信息
                System.out.println("④线程信息 id = " + Thread.currentThread().getId() +
                        ";名字 = " + Thread.currentThread().getName());
                // 阻塞
                System.out.println("等待读取客户端数据...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端的信息
                    System.out.println(new String(bytes, 0, read));
                } else {
                    // 读取完毕
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
