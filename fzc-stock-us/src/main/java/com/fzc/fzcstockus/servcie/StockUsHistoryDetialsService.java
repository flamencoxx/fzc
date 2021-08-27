package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.model.StockUsInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/8/9 17:34
 */
public interface StockUsHistoryDetialsService extends IService<StockUsInfo> {

    String showDescription(String code);

    boolean fetchHistoryPrice(String symbol);
}
