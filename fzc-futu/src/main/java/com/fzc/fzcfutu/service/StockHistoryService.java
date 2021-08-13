package com.fzc.fzcfutu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcfutu.model.StockInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 9:12
 */
public interface StockHistoryService extends IService<StockInfo> {

    Boolean fetchHistoryData(String stockCode);
}
