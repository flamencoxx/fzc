package com.fzc.fzcstockus.mutiThread;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;


import javax.annotation.Resource;
import java.util.concurrent.Callable;


/**
 * @author Flamenco.xxx
 * @date 2021/9/12 9:14
 */
public class CallableFindStock implements Callable<StockUsInfoDo> {

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CallableFindStock() {
    }

    @Resource
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Override
    public StockUsInfoDo call() throws Exception {
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        return stock;
    }
}
