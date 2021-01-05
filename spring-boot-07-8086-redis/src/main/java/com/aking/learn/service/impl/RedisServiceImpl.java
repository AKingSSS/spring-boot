package com.aking.learn.service.impl;

import com.aking.learn.service.RedisService;
import com.aking.learn.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 测试
     */
    @Override
    public void test() {
        String key = "test_2021_01_05";
        redisUtil.set(key, "aking", 60 * 3);
        String str = (String)redisUtil.get(key);
        log.info("str = "+ str);
    }
}
