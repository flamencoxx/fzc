package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.model.StockUsInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/8/27 11:15
 */
public interface StockUsPEService extends IService<StockUsInfo> {

    public boolean fetchPEList(String symbol);
}
