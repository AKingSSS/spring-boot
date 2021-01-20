package com.aking.learn._04_nio._02_channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  写文件
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class FileChannelDemo01 {
    public static void main(String[] args) throws Exception {
        String str = "hello,杨青沙";
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        // 根据输出流获取一个 channel
        FileChannel channel = fileOutputStream.getChannel();
        // 将 str 写入 byteBuffer中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        // 切换 byteBuffer
        byteBuffer.flip();
        // 将 byteBuffer 写入到 channel
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
