package com.fzc.fzcstockus.thread;

import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/8/11 13:43
 */
@SpringBootTest
public class TestThread {
    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    FetchBasicFinancial fetchBasicFinancial;
    @Test
    public void threadTesting1(){


        String code = "IBM";

        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(6,10,60,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));

        fetchBasicFinancial.setCode(code);
        threadPool.execute(fetchBasicFinancial);

    }

    @Test
    public void testing(){
        String code ="IBM";
        BasicFinancials basicFinancials = stockBasicFinancialService.findBasicFinancialsBySymbol(code);
        System.out.println(basicFinancials);
    }
}
