package com.aking.learn.service;

import com.aking.learn.utils.ResponseResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  T2:dto
 *  T3:query
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public interface BaseService<T2,T3> {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    ResponseResult<T2> queryById(Long id);

    /**
     * 根据id集合查询
     * @param ids
     * @return
     */
    ResponseResult<List<T2>> queryByIds(List<Long> ids);

    /**
     * 分页查询
     * @param query
     * @return
     */
    ResponseResult<PageInfo<T2>> queryPageList(T3 query);

    /**
     * 不分页查询
     * @param query
     * @return
     */
    ResponseResult<List<T2>> queryNoPageList(T3 query);

    /**
     * 数量查询
     * @param query
     * @return
     */
    ResponseResult<Long> count(T3 query);

    /**
     * 增加数据
     * @param dto
     * @return
     */
    ResponseResult<Boolean> add(T2 dto);

    /**
     * 根据id修改数据
     * @param dto
     * @return
     */
    ResponseResult<Boolean> updateById(T2 dto);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    ResponseResult<Boolean> deleteById(Long id);
}
