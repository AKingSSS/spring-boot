package com.aking.learn._04_nio._02_channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  读文件
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class FileChannelDemo02 {
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\file01.txt");
        // 创建输出流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 根据输出流获取一个 channel
        FileChannel channel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将channel的数据读入到 byteBuffer
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
