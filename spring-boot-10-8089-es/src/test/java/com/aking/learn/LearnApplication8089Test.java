package com.aking.learn;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSON;
import com.aking.learn.domain.User;
import com.aking.learn.utils.ElasticsearchUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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
}
