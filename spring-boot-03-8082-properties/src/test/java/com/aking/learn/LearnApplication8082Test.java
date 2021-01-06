package com.aking.learn;

import com.aking.learn.domain.Dog;
import com.aking.learn.domain.Person;
import com.aking.learn.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@SpringBootTest
public class LearnApplication8082Test {
    @Autowired
    private Person person;
    @Autowired
    private Dog dog;
    @Autowired
    private User user;

    /**
     * 通过 @value 获取值
     */
    @Test
    public void test1() {
        System.out.println("person = " + person);
    }

    /**
     * 通过 @ConfigurationProperties 获取值
     */
    @Test
    public void test2() {
        System.out.println("dog = " + dog);
    }

    /**
     * 读取自定义配置参数
     */
    @Test
    public void test3() {
        System.out.println("user = " + user);
    }
}
