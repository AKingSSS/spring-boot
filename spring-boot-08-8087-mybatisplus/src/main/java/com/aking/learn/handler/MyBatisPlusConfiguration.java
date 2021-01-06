package com.aking.learn.handler;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Configuration
public class MyBatisPlusConfiguration {
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
