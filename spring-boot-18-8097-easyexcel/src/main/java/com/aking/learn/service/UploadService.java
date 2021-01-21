package com.aking.learn.service;

import com.aking.learn.pojo.UploadData;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-21
 */
public interface UploadService {
    /**
     * 批量保存数据
     * @param dataList
     * @return
     */
    Boolean batchSaveData(List<UploadData> dataList);
}
