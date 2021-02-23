package com.aking.learn.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-02-23
 */
public enum PayTypeEnum {
    ALI_PAY(1, "支付宝支付"),

    WECHAT_PAY(2, "微信支付"),

    UNION_PAY(3, "银联云闪付支付");

    private Integer code;
    private String desc;

    PayTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
