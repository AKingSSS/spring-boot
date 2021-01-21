package com.aking.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedissonController {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 分布式锁
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        RLock lock = null;
        try {
            lock = redissonClient.getLock("testLock");
            log.info("尝试获取锁， threadName：" + Thread.currentThread().getName());
            // 无参，watchdog 生效
            lock.lock();
            log.info("获取锁， threadName：" + Thread.currentThread().getName());
            // 模拟超时
            Thread.sleep(1000 * 20);
            log.info("抢到一瓶茅台，threadName：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "fail";
        } finally {
            log.info("释放锁，threadName：" + Thread.currentThread().getName());
            lock.unlock();
        }
        return "success";
    }
}
