package com.aking.learn._04_nio._02_channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class FileChannelDemo03 {
    public static void main(String[] args) throws Exception {
        // 获取输入流
        FileInputStream fileInputStream = new FileInputStream("d:\\file01.txt");
        // 根据输入流获取一个 channel
        FileChannel channel01 = fileInputStream.getChannel();

        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("file02.txt");
        // 根据输出流获取一个 channel
        FileChannel channel02 = fileOutputStream.getChannel();

        // 创建一个buffer 读取数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) {
            byteBuffer.clear();
            // 将通道的数据读入buffer中
            int read = channel01.read(byteBuffer);
            if (read == -1) {
                break;
            }
            // 将buffer数据写入到通道
            byteBuffer.flip();
            channel02.write(byteBuffer);

            // 关闭流
            fileInputStream.close();
            fileOutputStream.close();
        }
    }
}
