package com.aking.learn.mapper;

import com.aking.learn.domain.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 注解实现自定义sql
     * @param name
     * @return
     */
    @Select("select * from user where deleted = 0 and name=#{name}")
    User getUserByName(@Param("name") String name);

    /**
     * xml 实现自定义sql
     * @param id
     * @return
     */
    User getUserById(@Param("id") Long id);
}
