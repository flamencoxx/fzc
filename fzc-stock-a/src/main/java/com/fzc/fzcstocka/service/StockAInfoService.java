package com.fzc.fzcstocka.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstocka.model.StockAInfo;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:30
 */

public interface StockAInfoService  extends IService<StockAInfo> {

    StockAInfo searchByMoreKey(String code);

    void updateMarketValues();

    StockAInfo findByStockIdentity(String code);

    List<StockAInfo> searchIndustryList(String code);

    List<String> sortByValues(String code);

}
