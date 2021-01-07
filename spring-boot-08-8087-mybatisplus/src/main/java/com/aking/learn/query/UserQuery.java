package com.aking.learn.query;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Data
public class UserQuery extends PageParam implements Serializable {

    private static final long serialVersionUID = -8806479822579904826L;
    /**
     * 用户姓名
     */
    private String name;
}
