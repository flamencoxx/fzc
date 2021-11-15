package com.fzc.fzcstockus.servcie;

import com.fzc.fzcstockus.DO.StockUsInfoDo;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2021/11/8 17:57
 */
public interface AsyncStockUsInfoService {

    Future<StockUsInfoDo> asyncFindBySymbol(String symbol);



    Future<StockUsInfoDo> asyncFindById(Integer id);


    List<StockUsInfoDo> asyncFindAllStockInfo();


}
