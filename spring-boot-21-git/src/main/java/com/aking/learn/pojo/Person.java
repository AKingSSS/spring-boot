package com.aking.learn.pojo;

import java.io.Serializable;

/**
 * @author yangkang
 * @date 2021/8/1
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -8216224404881451376L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;
}
