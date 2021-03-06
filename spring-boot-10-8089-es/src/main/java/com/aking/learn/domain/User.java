package com.aking.learn.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-08
 */
@Data
@Accessors(chain = true)
public class User extends AbstractDataDomain{
    private String name;
    private Integer age;
}
