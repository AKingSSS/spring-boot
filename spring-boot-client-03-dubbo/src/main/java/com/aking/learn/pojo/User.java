package com.aking.learn.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-18
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 4389327374727292295L;

    private String name;
    private Integer age;
}
