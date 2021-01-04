package com.aking.learn;

import com.aking.learn.customer.MyBanner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Y10003453
 */
@SpringBootApplication
public class LearnApplication8080 {

    public static void main(String[] args) {
        // 默认
        //SpringApplication.run(LearnApplication8080.class, args);
        SpringApplication springApplication = new SpringApplication(LearnApplication8080.class);
        // banner 模式
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        //自定义 banner
        //springApplication.setBanner(new MyBanner());
        springApplication.run(args);
    }

}
