package com.aking.learn;

import com.aking.learn.domain.UserDO;
import com.aking.learn.dto.UserDTO;
import com.aking.learn.mapper.UserMapper;
import com.aking.learn.query.UserQuery;
import com.aking.learn.service.UserService;
import com.aking.learn.utils.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

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
    @Autowired
    private UserService userService;

    /**
     * 测试--查询所有列表
     */
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDO> userDOList = userMapper.selectList(null);
        userDOList.forEach(System.out::println);
    }


    /**
     * 测试--插入
     */
    @Test
    public void testAdd() {
        System.out.println(("----- testAdd method test ------"));
        UserDO userDO = new UserDO().setName("悟空").setAge(100).setEmail("18932848@qq.com");
        userMapper.insert(userDO);
        System.out.println("id =" + userDO.getId());
    }

    /**
     * 测试 --orderBy 排序功能
     */
    @Test
    public void testOrderBy() {
        System.out.println(("----- testOrderBy method test ------"));
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<UserDO> userDOList = userMapper.selectList(wrapper);
        userDOList.forEach(System.out::println);
    }

    /**
     * 测试 -- 时间区间筛选
     */
    @Test
    public void testTimeSearch() {
        System.out.println(("----- testTimeSearch method test ------"));
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.ge("create_time", "2020-12-01");
        wrapper.le("create_time", "2020-12-30");
        List<UserDO> userDOList = userMapper.selectList(wrapper);
        userDOList.forEach(System.out::println);
    }

    /**
     * 测试 -- 注解自定义sql
     */
    @Test
    public void testCustomerSql() {
        System.out.println(("----- testCustomerSql method test ------"));
        UserDO userDO = userMapper.getUserByName("悟空");
        System.out.println("user = " + userDO);
    }

    /**
     * 测试 -- xml自定义sql
     */
    @Test
    public void testXmlSql() {
        System.out.println(("----- testXmlSql method test ------"));
        UserDO userDO = userMapper.getUserById(3L);
        System.out.println("user = " + userDO);
    }

    /**
     * 测试 -- 封装测试
     */
    @Test
    public void add() {
        System.out.println(("----- add method test ------"));
        UserDTO userDTO = new UserDTO().setName("悟空z").setAge(90).setEmail("18932848@qq.com");

        ResponseResult<Boolean> result = userService.add(userDTO);
        System.out.println("result = " + new Gson().toJson(result));
    }

    /**
     * 测试-查询
     */
    @Test
    public void queryById() {
        System.out.println(("----- queryById method test ------"));

        ResponseResult<UserDTO> result = userService.queryById(2L);
        System.out.println("result = " + new Gson().toJson(result));
    }

    /**
     * 测试-更新
     */
    @Test
    public void updateById() {
        System.out.println(("----- updateById method test ------"));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L).setName("贝吉塔");
        ResponseResult<Boolean> result = userService.updateById(userDTO);
        System.out.println("result = " + new Gson().toJson(result));
    }

    /**
     * 逻辑删除
     */
    @Test
    public void deleted() {
        System.out.println(("----- deleted method test ------"));
        ResponseResult<Boolean> result = userService.deleteById(13L);
        System.out.println("result = " + new Gson().toJson(result));
    }

    @Test
    public void queryPageList() {
        System.out.println(("----- queryPageList method test ------"));
        UserQuery query = new UserQuery();
        query.setName("悟空");
        ResponseResult<PageInfo<UserDTO>> result = userService.queryPageList(query);
        System.out.println("result = " + new Gson().toJson(result));
    }

    @Test
    public void queryNoPageList() {
        System.out.println(("----- queryNoPageList method test ------"));
        UserQuery query = new UserQuery();
        query.setOrderBy("create_time");
        query.setAsc(false);
        ResponseResult<List<UserDTO>> result = userService.queryNoPageList(query);
        System.out.println("result = " + new Gson().toJson(result));
    }

}
