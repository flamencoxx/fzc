package com.fzc.fzcfutu.ServiceTesting;

import com.fzc.fzcfutu.service.StockFinancialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 17:55
 */
@SpringBootTest
public class fetchStockFinancialTesting {
    @Autowired
    private StockFinancialService stockFinancialService;

    @Test
    public void fetchFinancialInfo(){
        String code = "002603";
        boolean bool = stockFinancialService.fetchStockFinancial(code);
        System.out.println(bool);

    }
}
