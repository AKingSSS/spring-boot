package aking.learn;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Y10003453
 */
@SpringBootApplication
@EnableDubbo
public class LearnApplication8095 {

    public static void main(String[] args) {
        // 默认
        SpringApplication.run(LearnApplication8095.class, args);
    }
}
