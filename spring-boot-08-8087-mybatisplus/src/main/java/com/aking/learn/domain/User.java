package com.aking.learn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@Data
@Accessors(chain = true) // 链式调用
public class User extends AbstractDataDomain {
    @TableId (type=IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
