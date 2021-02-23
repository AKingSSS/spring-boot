package com.aking.learn.service.impl;

import com.aking.learn.service.Payment;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-23
 */
@Service("aliPayPayment")
public class AliPayPayment implements Payment {
    /**
     * 支付方式
     */
    @Override
    public void payment() {
        System.out.println("支付宝支付");
    }
}
