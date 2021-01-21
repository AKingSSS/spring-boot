package com.aking.learn.service.impl;

import com.aking.learn.pojo.UploadData;
import com.aking.learn.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-21
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    /**
     * 批量保存数据
     *
     * @param dataList
     * @return
     */
    @Override
    public Boolean batchSaveData(List<UploadData> dataList) {
        try {
            // 模拟数据库操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("batchSaveData error:[{}]", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
