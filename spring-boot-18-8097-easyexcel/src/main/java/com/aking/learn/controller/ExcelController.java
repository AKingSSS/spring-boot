package com.aking.learn.controller;

import com.aking.learn.pojo.IndexData;
import com.aking.learn.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    /**
     * 下载单个sheet的excel
     * @param response
     */
    @GetMapping("/downloadOneSheet")
    public void downloadOneSheet(HttpServletResponse response) {
        List<IndexData> list = new ArrayList<IndexData>();
        for (int i = 0; i < 10; i++) {
            IndexData data = new IndexData();
            data.setString("字符串" + 0);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        String fileName = "test" + new Random().nextInt(100);
        try {
            ExcelUtil.downloadOneSheet(response, fileName, "统计", list, IndexData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
