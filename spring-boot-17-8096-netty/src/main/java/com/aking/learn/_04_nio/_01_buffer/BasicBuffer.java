package com.aking.learn._04_nio._01_buffer;

import java.nio.IntBuffer;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个 buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 往intBuffer中添加数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 读取intbuffer数据，将intbuffer转换，读写切换
        intBuffer.flip();

        // 读取数据
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get());
        }
    }
}
