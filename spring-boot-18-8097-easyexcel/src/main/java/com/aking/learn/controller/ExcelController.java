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
        List<IndexData> datas = new ArrayList<IndexData>();
        for (int i = 0; i < 10; i++) {
            IndexData data = new IndexData();
            data.setString("字符串" + 0);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            data.setSex(new Random().nextInt(3));
            datas.add(data);
        }

        String fileName = "test" + new Random().nextInt(100);
        try {

            ExcelUtil.downloadOneSheet(response, fileName, "统计", datas, IndexData.class, null);
            // 自定义表头
            //ExcelUtil.downloadOneSheet(response, fileName, "统计", datas, IndexData.class, head());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载多个sheet的excel
     * @param response
     */
    @GetMapping("/downloadManySheets")
    public void downloadManySheets(HttpServletResponse response) {
        List<List<IndexData>> list = new ArrayList<>();
        List<IndexData> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                IndexData data = new IndexData();
                data.setString("字符串" + 0);
                data.setDate(new Date());
                data.setDoubleData(0.56);
                data.setSex(new Random().nextInt(3));
                datas.add(data);
            }
            list.add(datas);
        }
        String fileName = "test" + new Random().nextInt(100);
        try {
            ExcelUtil.downloadManySheets(response, fileName, "统计", list, IndexData.class, null);
            // 自定义表头
            //ExcelUtil.downloadManySheets(response, fileName, "统计", list, IndexData.class, head());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义表头
     * 覆盖 @ExcelProperty
     * @return
     */
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }
}
