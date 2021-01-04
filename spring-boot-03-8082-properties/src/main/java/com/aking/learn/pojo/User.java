package com.aking.learn.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 读取自定义配置参数
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@Data
@Component
@PropertySource(value = {"classpath:user.properties"})
@ConfigurationProperties(prefix = "myuser")
public class User {
    private String name;
    private Integer age;
}
