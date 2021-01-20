package com.aking.learn.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
public enum SexEnum {
    man(1, "男"),
    woman(0, "女");

    public Integer code;
    public String desc;

    private SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static String getEnumDesc(Integer code) {
        SexEnum value = getEnum(code);
        if (value != null) {
            return value.desc;
        }
        return "";
    }

    public static SexEnum getEnum(Integer code) {
        SexEnum[] values = values();
        for (SexEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
