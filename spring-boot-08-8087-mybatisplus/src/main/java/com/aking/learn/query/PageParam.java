package com.aking.learn.query;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Data
public class PageParam {
    private int pageNum = 1;
    private int pageSize = 10;
}
