package com.aking.learn.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@Component
@Data
public class Person {
    /**
     * ${person.username} 这种写法称之为 SpEL
     */
    @Value("${person.username}")
    private String username;
    @Value("${person.age}")
    private Integer age;
}
