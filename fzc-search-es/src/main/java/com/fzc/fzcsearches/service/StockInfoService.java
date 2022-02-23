package com.fzc.fzcsearches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.model.StockAInfo;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:30
 */

public interface StockInfoService extends IService<StockAInfo> {

    /**
     * 将sql数据导入Es数据库
     * @return
     */
    int importAll();

    /**
     * A股的关键词搜索，包含ts_code,symbol,esName,name,fullName,industry等等
     * @param keyword
     * @return
     */
    List<EsStockAimport> searchByKeyword(String keyword);
}
