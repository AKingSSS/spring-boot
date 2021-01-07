package com.aking.learn.service.impl;

import com.aking.learn.query.PageParam;
import com.aking.learn.service.BaseService;
import com.aking.learn.utils.DataConvert;
import com.aking.learn.utils.PageUtils;
import com.aking.learn.utils.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
public abstract class BaseServiceImpl<T1, T2, T3 extends PageParam, M extends BaseMapper>
        extends DataConvert<T1, T2, T3> implements BaseService<T2, T3> {
    @Autowired
    protected M manager;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<T2> queryById(Long id) {
        if (id == null) {
            return ResponseResult.badRequest("id is null");
        }
        return ResponseResult.ok(convertToT2((T1) manager.selectById(id)));
    }

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @Override
    public ResponseResult<PageInfo<T2>> queryPageList(T3 query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        QueryWrapper<T1> queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(convertT3ToT1(query));
        queryWrapper.orderBy(true, query.isAsc(), query.getOrderBy());
        return ResponseResult.ok(PageUtils.convert(new PageInfo<>((List<T1>) manager.selectList(queryWrapper)), this::convertToT2));
    }

    /**
     * 不分页查询
     *
     * @param query
     * @return
     */
    @Override
    public ResponseResult<List<T2>> queryNoPageList(T3 query) {
        QueryWrapper<T1> queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(convertT3ToT1(query));
        queryWrapper.orderBy(true, query.isAsc(), query.getOrderBy());
        return ResponseResult.ok(convertToT2(manager.selectList(queryWrapper)));
    }

    /**
     * 数量查询
     *
     * @param query
     * @return
     */
    @Override
    public ResponseResult<Long> count(T3 query) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.setEntity(query);
        return ResponseResult.ok(Long.parseLong(manager.selectCount(queryWrapper) + ""));
    }

    /**
     * 增加数据
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult<Boolean> add(T2 dto) {
        if (dto == null) {
            return ResponseResult.badRequest("dto is null");
        }
        return ResponseResult.ok(manager.insert(convertToT1(dto)) == 1);
    }

    /**
     * 根据id修改数据
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult<Boolean> updateById(T2 dto) {
        if (dto == null) {
            return ResponseResult.badRequest("dto is null");
        }
        return ResponseResult.ok(manager.updateById(convertToT1(dto)) == 1);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Boolean> deleteById(Long id) {
        if (id == null) {
            return ResponseResult.badRequest("id is null");
        }
        return ResponseResult.ok(manager.deleteById(id) == 1);
    }

    /**
     * 根据id集合查询
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseResult<List<T2>> queryByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.badRequest("ids is null");
        }
        return ResponseResult.ok(convertToT2(manager.selectBatchIds(ids)));
    }
}
