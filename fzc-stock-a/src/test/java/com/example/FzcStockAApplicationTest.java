package com.example;

import cn.hutool.core.lang.Console;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.model.MarketSecuritiesInfo;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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


} 
