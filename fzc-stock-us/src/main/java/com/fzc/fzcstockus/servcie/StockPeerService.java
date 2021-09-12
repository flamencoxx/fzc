package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.StockUsInfo;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/7/29 9:37
 */
public interface StockPeerService extends IService<StockUsInfo> {



//    查询当前股票的同行业其他股票，只查询数据库中存在的数据，剔除其余。

    List<StockUsInfoDo> findPeerListBySymbol(String symbol);

    List<StockUsInfoDo> findPeerListBySymbolAsync(String symbol);

}
