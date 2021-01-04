package com.aking.learn.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-04
 */
public enum CustomerStatusEnum {
    UNUNITED(0, "未访"),
    FIRST_VISIT(1, "初访"),
    RETURN_VISIT(2, "回访"),
    INTENTION(3, "意向"),
    INVALID(4, "无效"),
    ENTER(5, "入驻"),
    AUTHENTICATION(6, "认证");

    public Integer code;
    public String desc;

    public Integer getCode() {
        return this.code;
    }

    private CustomerStatusEnum(Integer status, String desc) {
        this.code = status;
        this.desc = desc;
    }

    public static CustomerStatusEnum getEnum(Integer code) {
        CustomerStatusEnum[] values = values();
        CustomerStatusEnum[] var2 = values;
        int var3 = values.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            CustomerStatusEnum value = var2[var4];
            if (value.code.equals(code)) {
                return value;
            }
        }

        return null;
    }

    public static String getEnumDesc(Integer code) {
        CustomerStatusEnum value = getEnum(code);
        return value != null ? value.desc : "";
    }

    public boolean equals(Integer code) {
        return code == null ? false : this.code.equals(code);
    }

}
