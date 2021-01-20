package com.aking.learn.handle;

import com.aking.learn.enums.SexEnum;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-20
 */
public class SexConverter implements Converter<Integer> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
                                     GlobalConfiguration globalConfiguration) throws Exception {

        return SexEnum.man.desc.equals(cellData.getStringValue()) ? SexEnum.man.code : SexEnum.woman.code;
    }

    @Override
    public CellData convertToExcelData(Integer value, ExcelContentProperty excelContentProperty,
                                       GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value.equals(SexEnum.man.code) ? SexEnum.man.desc : SexEnum.woman.desc);
    }
}
