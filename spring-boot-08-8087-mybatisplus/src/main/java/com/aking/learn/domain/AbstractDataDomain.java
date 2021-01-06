package com.aking.learn.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
@Data
public abstract class AbstractDataDomain {
    private Long creatorId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private Long lastModifierId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;
    @TableLogic
    private Integer deleted;
}
