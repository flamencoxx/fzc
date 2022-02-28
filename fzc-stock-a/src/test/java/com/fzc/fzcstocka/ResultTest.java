package com.fzc.fzcstocka;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
public class ResultTest {

    @Autowired
    private StockAInfoRepository dao;

    @Resource
    private StockAInfoRepository stockAInfoRepository;

    @Autowired
    private ResultAnalyzerRepository resultDao;

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private StockAInfoRepository repository;

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
    String code = "603800.SSE";
    String code1 = "";
    String code2 = "fzc";
    String code3 = "603803";
    StockAInfo stock = stockAInfoService.searchByMoreKey(code1);
    if (ObjectUtil.isNotEmpty(stock)){
        Console.log(stock.toString());
    }
    StockAInfo stock1 = stockAInfoService.searchByMoreKey(code2);
    if (ObjectUtil.isNotEmpty(stock1)){
        Console.log(stock1.toString());
    }
    StockAInfo stock2 = stockAInfoService.searchByMoreKey(code3);
    if (ObjectUtil.isNotEmpty(stock2)){
        Console.log(stock2.toString());
    }
}

    @Test
    public void testMain2() throws Exception {
//TODO: Test goes here...
        ResultAnalyzer result = new ResultAnalyzer();
        Multiset<String> set = HashMultiset.create();
        List<ResultAnalyzer> list = resultDao.findAll();
        list.forEach(k->{
           set.add(k.getAnalyzer());
        });
        set.elementSet().forEach(k->{
           Console.log(k + ":" + set.count(k));
        });
    }

    @Test
    public void testMain3() throws Exception {
//TODO: Test goes here...

    }

    @Test
    public void testMain4() throws Exception {
//TODO: Test goes here...

    }

    @Test
    public void testMain5() throws Exception {
//TODO: Test goes here...

    }

    @Test
    public void testMain6() throws Exception {
//TODO: Test goes here...

    }

    @Test
    public void testMain7() throws Exception {
//TODO: Test goes here...

    }




} 
