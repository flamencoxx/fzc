package com.fzc.fzcsearchneo4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearchneo4j.model.StockUsImport;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 11:06
 */
public interface StockUsImportService extends IService<StockUsImport> {

    /**
     * 初始化图形数据库
     */
    void initialize();
}
