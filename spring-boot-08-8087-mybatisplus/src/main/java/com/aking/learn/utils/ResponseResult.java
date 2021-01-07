package com.aking.learn.utils;

import cn.hutool.core.util.StrUtil;
import com.aking.enums.ResponseEnums;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;

/**
 * <p>
 * 相应数据
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -1990396326681005L;

    public static final int RESULT_OK = 0;
    private static final int RESULT_ERROR = 400;
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 详细响应消息
     */
    private String detail;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 接口数据
     */
    private T data;
    /**
     * 元数据
     */
    private JSONObject meta;

    public static <T> ResponseResult<T> ok(T data) {
        ResponseResult r = new ResponseResult();
        r.code = RESULT_OK;
        r.message = "SUCCESS";
        r.timestamp = System.currentTimeMillis();
        r.data = data;
        return r;
    }

    public static <T> ResponseResult<T> ok(T data, JSONObject meta) {
        ResponseResult r = new ResponseResult();
        r.code = RESULT_OK;
        r.message = "SUCCESS";
        r.timestamp = System.currentTimeMillis();
        r.data = data;
        r.meta = meta;
        return r;
    }

    public static <T> ResponseResult<T> badRequest(int code) {
        ResponseResult<T> r = new ResponseResult();
        r.code = code;
        r.message = "发生错误!";
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> ResponseResult<T> badRequest(String message) {
        ResponseResult<T> r = new ResponseResult();
        r.code = RESULT_ERROR;
        r.message = StrUtil.hasEmpty(message) ? "发生错误" : message;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> ResponseResult<T> badRequest(String message, String detail) {
        ResponseResult<T> r = new ResponseResult();
        r.code = RESULT_ERROR;
        r.message =  StrUtil.hasEmpty(message) ? "发生错误" : message;
        r.detail =  StrUtil.hasEmpty(detail) ? "发生错误" : detail;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> ResponseResult<T> badRequest(int code, String message) {
        ResponseResult<T> r = new ResponseResult();
        r.code = code;
        r.message = StrUtil.hasEmpty(message) ? "发生错误" : message;
        r.timestamp = System.currentTimeMillis();
        return r;
    }
    public static <T> ResponseResult<T> badRequest(ResponseEnums responseEnums) {
        ResponseResult<T> r = new ResponseResult();
        r.code = responseEnums.code;
        r.message = responseEnums.desc;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public static <T> ResponseResult<T> badRequest(int code, String message, String detail) {
        ResponseResult<T> r = badRequest(message, detail);
        r.code = code;
        return r;
    }

    public static <T> ResponseResult<T> badRequest(String message, T data) {
        ResponseResult<T> r = new ResponseResult();
        r.code = RESULT_ERROR;
        r.message = StrUtil.hasEmpty(message) ? "发生错误" : message;
        r.timestamp = System.currentTimeMillis();
        r.data = data;
        return r;
    }

    public boolean isSuccess() {
        return this.code == RESULT_OK;
    }

}
