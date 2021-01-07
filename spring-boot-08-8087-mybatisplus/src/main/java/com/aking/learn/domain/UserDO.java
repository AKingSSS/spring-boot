package com.aking.learn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@TableName(value = "user")
@NoArgsConstructor
public class UserDO extends AbstractDataDomain {
    @TableId (type=IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
