package com.example;

import cn.hutool.core.lang.Console;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.model.MarketSecuritiesInfo;
import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.ResultNameUtil;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

/** 
* FzcStockAApplication Tester. 
* 
* @author <Authors name> 
* @since <pre>2�� 21, 2022</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FzcStockAApplication.class)
public class FzcStockAApplicationTest {

    @Autowired
    private StockAInfoRepository dao;

    @Resource
    private StockAInfoRepository stockAInfoRepository;

    @Autowired
    private ResultAnalyzerRepository resultDao;

    @Autowired
    private StockAInfoService stockAInfoService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here...
    String code = "000001";
    Console.log(code);
    MarketSecuritiesInfo stock = dao.findBySymbol(code);
    Console.log(stock);
}

    @Test
    public void testMain2() throws Exception {
//TODO: Test goes here...
        String analyzer = "3ee3a4ff-a2cf-4244-8f45-c319016ee16b";
        String stockIdentity = "000001.SZSE";
        List<ResultAnalyzer> list = resultDao.findByAnalyzerAndStockIdentity(analyzer,stockIdentity);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void testMain3() throws Exception {
//TODO: Test goes here...
//        String analyzer = "3ee3a4ff-a2cf-4244-8f45-c319016ee16b";
        String stockIdentity = "600517.SSE";
        List<ResultAnalyzer> list = resultDao.findByAnalyzerAndStockIdentity(ResultNameUtil.getCFId(),stockIdentity);
        list.forEach(k->{
            Console.log(k.getPeriod().toString());
        });
    }

    @Test
    public void testMain4() throws Exception {
//TODO: Test goes here...
        List<MarketSecuritiesInfo> list = dao.findAll();
        list.forEach(k->{
            Console.log(k.getName());

        });
    }

    @Test
    public void testMain5() throws Exception {
//TODO: Test goes here...
        List<MarketSecuritiesInfo> list = dao.findAll();
        List<StockAInfo> stockList = Lists.newArrayList();
        list.forEach(k->{
            StockAInfo stock = new StockAInfo();
            stock.setStockIdentity(k.getStockIdentity());
            stock.setName(k.getName());
            stock.setArea(k.getArea());
            stock.setCode(k.getCode());
            stock.setCurrType(k.getCurrType());
            stock.setEnname(k.getEnname());
            stock.setExchange(k.getExchange());
            stock.setFullname(k.getFullname());
            stock.setIndustry(k.getIndustry());
            stock.setMarket(k.getMarket());
            stock.setSymbol(k.getSymbol());
            stock.setTsCode(k.getTsCode());
            try {
                stock.setListingDate(DateFormat.getDateInstance().parse(k.getListingDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            stockList.add(stock);
        });

        boolean res = stockAInfoService.saveBatch(stockList);
        Console.log(res);
    }

    @Test
    public void testMain6() throws Exception {
//TODO: Test goes here...
        List<MarketSecuritiesInfo> list = dao.findAll();
        List<StockAInfo> stockList = Lists.newArrayList();
        list.forEach(k->{
            Console.log(k.getName());

        });
    }


} 
