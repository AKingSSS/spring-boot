package com.aking.learn._04_nio._02_channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * <p>
 *  拷贝
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class FileChannelDemo04 {
    public static void main(String[] args) throws Exception {
        // 获取输入流
        FileInputStream fileInputStream = new FileInputStream("d:\\test.jpg");
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("test.jpg");

        // 获取各个流对应的channel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        destCh.transferFrom(sourceCh,0,sourceCh.size());

        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
