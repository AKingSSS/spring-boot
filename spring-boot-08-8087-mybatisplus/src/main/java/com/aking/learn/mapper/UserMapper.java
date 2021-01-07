package com.aking.learn.mapper;

import com.aking.learn.domain.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 注解实现自定义sql
     * @param name
     * @return
     */
    @Select("select * from user where deleted = 0 and name=#{name}")
    UserDO getUserByName(@Param("name") String name);

    /**
     * xml 实现自定义sql
     * @param id
     * @return
     */
    UserDO getUserById(@Param("id") Long id);
}
