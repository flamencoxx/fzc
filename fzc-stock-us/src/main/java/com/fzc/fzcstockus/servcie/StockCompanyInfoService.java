package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.StockUsInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/7/20 19:47
 */
public interface StockCompanyInfoService extends IService<StockUsInfo> {

    //查找美股公司情况并更新

    public StockUsInfoDo findStockCompanyInfo(String symbol);

}
