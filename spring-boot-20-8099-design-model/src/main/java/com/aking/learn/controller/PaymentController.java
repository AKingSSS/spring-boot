package com.aking.learn.controller;

import com.aking.learn.context.PaymentContext;
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
 * @date 2021-02-23
 */
@RestController
@RequestMapping("/apis")
public class PaymentController {
    @Autowired
    private PaymentContext paymentContext;

    /**
     * 支付
     * @param name :aliPayPayment
     */
    @GetMapping("payment")
    public String payment(String name) {
        paymentContext.payment(name);
        return "success";
    }

}
