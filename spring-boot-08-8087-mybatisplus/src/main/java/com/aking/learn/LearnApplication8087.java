package com.aking.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Y10003453
 */
@SpringBootApplication
@MapperScan("com.aking.learn.mapper")
public class LearnApplication8087 {

    public static void main(String[] args) {
        // 默认
        SpringApplication.run(LearnApplication8087.class, args);
    }

}
