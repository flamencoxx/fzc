package com.fzc.fzcstockus.mutiThread;

import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Flamenco.xxx
 * @date 2021/8/26 8:49
 */
@Component
@Slf4j
public class FetchHistoryPrice implements Runnable {

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockUsHistoryDetialsService stockUsHistoryDetialsService;

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public FetchHistoryPrice(String symbol) {
        this.symbol = symbol;
    }

    public FetchHistoryPrice() {
    }

    @Override
    public void run() {
        boolean result = stockUsHistoryDetialsService.fetchHistoryPrice(symbol.toUpperCase());
        log.info("调用美股股价历史数据结果:" + result);
    }
}
