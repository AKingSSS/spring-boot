package com.aking.learn.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public enum ResponseEnums {
    SUCCESS(0, "SUCCESS"),
    ERROR(400, "ERROR"),
    BUSINESS_ERROR(401, "业务异常"),
    INTERFACE_ERROR(402, "接口异常"),
    NO_LOGIN(410, "未登录"),
    UNKNOWN(500, "未知异常"),
    NETWORK_ERROR(501, "网络异常，请稍后再试"),
    TIMEOUT_ERROR(502, "连接超时，请稍后再试");

    public Integer code;
    public String desc;

    private ResponseEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
