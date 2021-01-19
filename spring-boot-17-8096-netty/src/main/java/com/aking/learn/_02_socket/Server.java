package com.aking.learn._02_socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8096);
            System.out.println("服务端已启动，等待客户端的连接...");

            // 侦听并接受到此套接字的连接,返回一个Socket对象
            Socket socket = serverSocket.accept();

            //根据输入输出流和客户端连接
            InputStream inputStream = socket.getInputStream();
            // 提高效率，将自己字节流转为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //加入缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = null;
            String info = "";
            while ((temp = bufferedReader.readLine()) != null) {
                info += temp;
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为：" + socket.getInetAddress().getHostAddress());
            }
            //获取一个输出流，向服务端发送信息
            OutputStream outputStream = socket.getOutputStream();
            //将输出流包装成打印流
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print("你好，服务端已接收到您的信息");
            printWriter.flush();
            //关闭输出流
            socket.shutdownOutput();

            //关闭相对应的资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
