package com.aking.learn.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
@Slf4j
public class ExcelUtil {

    /**
     * 下载单一sheet的 excel
     *
     * @param response
     * @param fileName
     * @param list
     */
    public static <T> void downloadOneSheet(HttpServletResponse response, String fileName,
                                            String sheetName, List<T> list, Class<T> tClass,
                                            List<List<String>> heads) throws Exception {
        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(getOutputStream(response, fileName), tClass)
                .sheet(sheetName)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
        if (CollectionUtil.isEmpty(heads)) {
            excelWriterSheetBuilder
                    .doWrite(list);
        } else {
            excelWriterSheetBuilder
                    .head(heads)
                    .doWrite(list);
        }
    }

    /**
     * 下载多个sheet的 excel
     *
     * @param response
     * @param fileName
     * @param list
     */
    public static <T> void downloadManySheets(HttpServletResponse response, String fileName,
                                              String sheetName, List<List<T>> list, Class<T> tClass,
                                              List<List<String>> heads) throws Exception {
        ExcelWriter excelWriter = EasyExcel.write(getOutputStream(response, fileName), tClass).build();
        if (!CollectionUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(i, sheetName + (i + 1));
                WriteSheet writeSheet = null;
                if (CollectionUtil.isEmpty(heads)) {
                    writeSheet = excelWriterSheetBuilder
                            .build();
                } else {
                    writeSheet = excelWriterSheetBuilder
                            .head(heads)
                            .build();
                }
                excelWriter.write(list.get(i), writeSheet);
            }
        }
        excelWriter.finish();
    }

    /**
     * 浏览器 response 设置
     *
     * @param fileName
     * @param response
     * @return
     * @throws Exception
     */
    public static OutputStream getOutputStream(HttpServletResponse response, String fileName)
            throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //此处指定了文件类型为xls，如果是xlsx的，请自行替换修改
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出文件失败！");
        }
    }

}
