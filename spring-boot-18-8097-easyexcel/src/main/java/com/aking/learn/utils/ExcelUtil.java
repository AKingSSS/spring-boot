package com.aking.learn.utils;

import com.alibaba.excel.EasyExcel;
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
     * 下载单sheet的 excel
     *
     * @param response
     * @param fileName
     * @param list
     */
    public static <T> void downloadOneSheet(HttpServletResponse response, String fileName,
                                            String sheetName, List<T> list, Class<T> tClass) throws Exception {
        EasyExcel.write(getOutputStream(response, fileName), tClass).sheet(sheetName).doWrite(list);
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
