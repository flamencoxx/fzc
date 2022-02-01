package com.fzc.fzcstockus;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.Annual;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.model.Metric;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.FinancialsReportService;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author flamenco.xxx
 * @date 2021/12/17 9:54
 */
@SpringBootTest
public class ReportTest {

    @Autowired
    private FinancialsReportService financialsReportService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Test
    public void test1(){
        TimeInterval timer = new TimeInterval();
        String code = "ibm";
        FinancialsReported financialsReported = financialsReportService.getReportFast(code);
//        Console.log(financialsReported);
        Console.log(timer.interval());
    }

    @Test
    public void test2(){
        TimeInterval timer = new TimeInterval();
        String code = "IBM";
        BasicFinancials basicFinancials = stockBasicFinancialService.findBasicFinancialsBySymbol(code);
        Console.log(basicFinancials);
        Console.log(timer.interval());
    }

    @Test
    public void test3(){
        TimeInterval timer = new TimeInterval();
        String code = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code);
        Console.log(stock);
        Console.log(timer.interval());
    }

    @Test
    public void test4(){
        TimeInterval timer = new TimeInterval();
        String code = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code);
        BasicFinancials basicFinancials = stock.getBasicFinancials();
        Metric str = basicFinancials.getMetric();
        String type = basicFinancials.getMetricType();
        Annual annual = basicFinancials.getAnnual();
        Console.log(str);
        Console.log(type);
        Console.log(annual);
        Console.log(timer.interval());
    }

    @Test
    public void test5(){
        TimeInterval timer = new TimeInterval();
        String code = "TWNT";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code);
        BasicFinancials basicFinancials = stock.getBasicFinancials();
        Metric str = basicFinancials.getMetric();
        String type = basicFinancials.getMetricType();
        Annual annual = basicFinancials.getAnnual();
        Console.log(str);
        Console.log(type);
        Console.log(annual);
        Console.log(timer.interval());
    }

}
