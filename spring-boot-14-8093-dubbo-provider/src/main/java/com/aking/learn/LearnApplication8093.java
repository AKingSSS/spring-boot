package com.aking.learn;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Y10003453
 */
@SpringBootApplication
@EnableDubbo
public class LearnApplication8093 {

    public static void main(String[] args) {
        // 默认
        SpringApplication.run(LearnApplication8093.class, args);
    }
}
