package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.model.StockUsInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/7/25 10:06
 */
public interface StockBasicFinancialService extends IService<StockUsInfo> {


    void updateStockBasicFinancial(String symbol);

    BasicFinancials findBasicFinancialsBySymbol(String symbol);


}
