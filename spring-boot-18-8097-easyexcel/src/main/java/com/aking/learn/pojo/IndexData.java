package com.aking.learn.pojo;

import com.aking.learn.handle.CustomStringStringConverter;
import com.aking.learn.handle.SexConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
@Data
public class IndexData {
    @ExcelProperty(value = "字符串标题", index = 0, converter = CustomStringStringConverter.class)
    private String string;
    @ExcelProperty(value = "日期标题", index = 1)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date date;
    /**
     * 这里设置3 会导致第二列空的
     */
    @ExcelProperty(value = "数字标题", index = 3)
    @NumberFormat("#.##%")
    private Double doubleData;
    @ExcelProperty(value = "性别", converter = SexConverter.class)
    private Integer sex;
    @ExcelIgnore
    private String remark;
}
