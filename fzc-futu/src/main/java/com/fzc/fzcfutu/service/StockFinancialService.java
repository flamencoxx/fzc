package com.fzc.fzcfutu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcfutu.model.StockInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 10:35
 */
public interface StockFinancialService extends IService<StockInfo> {

    public boolean fetchStockFinancial(String code);


}
