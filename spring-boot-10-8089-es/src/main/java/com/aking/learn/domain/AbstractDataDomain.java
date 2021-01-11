package com.aking.learn.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-11
 */
@Data
@Accessors(chain = true)
public abstract class AbstractDataDomain {
    private Long id;
}
