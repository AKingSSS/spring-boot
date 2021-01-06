package com.aking.learn;

import com.aking.learn.mapper.UserMapper;
import com.aking.learn.domain.User;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-05
 */
@SpringBootTest
public class LearnApplication8087Test {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试--查询所有列表
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    /**
     * 测试--插入
     */
    @Test
    public void testAdd() {
        System.out.println(("----- testAdd method test ------"));
        User user = new User().setName("悟空").setAge(100).setEmail("18932848@qq.com");
        userMapper.insert(user);
        System.out.println("id =" + user.getId());
    }

    /**
     * 测试 --orderBy 排序功能
     */
    @Test
    public void testOrderBy() {
        System.out.println(("----- testOrderBy method test ------"));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 测试 -- 时间区间筛选
     */
    @Test
    public void testTimeSearch() {
        System.out.println(("----- testTimeSearch method test ------"));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("create_time", "2020-12-01");
        wrapper.le("create_time", "2020-12-30");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 测试 -- 注解自定义sql
     */
    @Test
    public void testCustomerSql() {
        System.out.println(("----- testCustomerSql method test ------"));
        User user = userMapper.getUserByName("悟空");
        System.out.println("user = " + user);
    }

    /**
     * 测试 -- xml自定义sql
     */
    @Test
    public void testXmlSql() {
        System.out.println(("----- testXmlSql method test ------"));
        User user = userMapper.getUserById(3L);
        System.out.println("user = " + user);
    }
}
