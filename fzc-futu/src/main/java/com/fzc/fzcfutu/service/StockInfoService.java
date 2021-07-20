package com.fzc.fzcfutu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.model.StockInfo;

import java.util.List;

/**
 * @author 11615
 */
public interface StockInfoService extends IService<StockInfo> {

     void refreshStockInfo();

     void refreshAllStockInfo(int start,int end);

     void refreshAllStockHistory(int start,int end);

     List<StockInfoDO.StockHistoricalData> findStockHistoryBySymbol(String symbol);
}
