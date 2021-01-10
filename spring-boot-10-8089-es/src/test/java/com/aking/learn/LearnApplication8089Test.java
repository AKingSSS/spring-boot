package com.aking.learn;

import cn.hutool.core.convert.Convert;
import com.aking.learn.domain.User;
import com.aking.learn.utils.ElasticsearchUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试es功能
 * </p>
 *
 * @author yk
 * @date 2021-01-08
 */
@SpringBootTest
@Slf4j
public class LearnApplication8089Test {
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    private static final String INDEX = "platform_user";

    /**
     * 创建索引（数据库）
     */
    @Test
    public void createIndex() {
        try {
            elasticsearchUtil.createIndex(INDEX);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("createIndex error:[{}]", e);
        }
    }

    /**
     * 增
     */
    @Test
    public void add() {
        User user = new User().setName("qingqing").setAge(14).setId(3L);
        try {
            elasticsearchUtil.addData(user, INDEX, user.getId().toString());
        } catch (IOException e) {
            log.error("addData error:[{}]", e);
        }
    }

    /**
     * 批量增加
     */
    @Test
    public void bulkAdd() {
        User user1 = new User().setName("qingqing").setAge(14).setId(101L);
        User user2 = new User().setName("qingqing").setAge(14).setId(102L);
        User user3 = new User().setName("qingqing").setAge(14).setId(103L);
        User user4 = new User().setName("qingqing").setAge(14).setId(104L);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        elasticsearchUtil.bulkPost(INDEX, users);
    }

    /**
     * 更新
     */
    @Test
    public void update() {
        User user = new User().setName("qing").setAge(18).setId(1L);
        try {
            elasticsearchUtil.updateDataById(user, INDEX, user.getId().toString());
        } catch (IOException e) {
            log.error("update error:[{}]", e);
        }
    }

    /**
     * 查询
     */
    @Test
    public void searchDataById() {
        try {
            Map<String, Object> map = elasticsearchUtil.searchDataById(INDEX, "1", null);
            User user = Convert.convert(User.class, map);
            System.out.println("user = " + user);
        } catch (IOException e) {
            log.error("searchDataById error:[{}]", e);
        }
    }

    /**
     * 批量查询
     */
    @Test
    public void bulkSearchByIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        try {
            List<Map<String, Object>> maps = elasticsearchUtil.searchDataByIds(INDEX, ids);
            log.info("maps = " + new Gson().toJson(maps));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复合查询
     */
    @Test
    public void multiSearch() {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // 模糊查询，失效？？？
            //sourceBuilder.query(QueryBuilders.fuzzyQuery("name","qing"));
            // 根据字段查询(精确查找)
            //sourceBuilder.query(QueryBuilders.termQuery("name", "qing"));
            // 通配符查询
            //sourceBuilder.query(QueryBuilders.wildcardQuery("name","*qing*"));
            // 范围查询
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
            rangeQueryBuilder.gte(18);
            sourceBuilder.query(rangeQueryBuilder);
            List<Map<String, Object>> list = elasticsearchUtil.searchListData(INDEX, sourceBuilder,
                    100, 0, null, "age",
                    false, "");
            log.info("list = " + new Gson().toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
