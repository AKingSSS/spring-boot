package com.aking.learn.context;

import cn.hutool.core.util.StrUtil;
import com.aking.learn.service.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-23
 */
@Component
public class PaymentContext {
    /**
     * 使用线程安全的ConcurrentHashMap存储所有实现Payment接口的Bean
     * key:payType
     * value：实现Payment接口Bean
     */
    private final Map<String, Payment> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public PaymentContext(Map<String, Payment> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    /**
     * 支付
     * @param payType
     */
    public void payment(String payType) {
        //在这里要捕捉异常，或者是做相应处理，因为如果找不到service名字
        //会报错，也就是找不到对应的策略
        if (!StrUtil.isEmpty(payType)) {
            strategyMap.get(payType).payment();
        }
    }
}
