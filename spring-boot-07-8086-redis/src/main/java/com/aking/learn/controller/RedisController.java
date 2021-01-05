package com.aking.learn.controller;

import com.aking.learn.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/test")
    public void test() {
        redisService.test();
    }
}
