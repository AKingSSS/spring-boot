package com.aking.learn.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1245326679923299519L;

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long creatorId;
    private Date createTime;
    private Long lastModifierId;
    private Date modifiedTime;
    private Integer deleted;
}