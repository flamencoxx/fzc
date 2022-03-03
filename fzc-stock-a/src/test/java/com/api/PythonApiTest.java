package com.api;

import cn.hutool.core.lang.Console;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/** 
* FactorApiServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>3�� 1, 2022</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FzcStockAApplication.class)
public class PythonApiTest {

    public static final String STOCK_AINFO = "stockAInfo";

    @Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception {
}

    @Autowired
    private FactorApiService factorApiService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;


    @Autowired
    private RedisService redisService;


    @Autowired
    private StockAInfoService stockAInfoService;

    @Test
    public void test1() throws Exception {
//TODO: Test goes here...
        Console.log("test");
        String key = REDIS_DATABASE + ":" + STOCK_AINFO;
        List<StockAInfo> stockAInfoList = stockAInfoService.list();
        Multimap<String,String> map = HashMultimap.create();
        stockAInfoList.forEach(k->{
            map.put(k.getStockIdentity(), k.getCode());
            map.put(k.getStockIdentity(), k.getSymbol());
            map.put(k.getStockIdentity(), k.getTsCode());
        });
//        redisService.del(key);
        redisService.set(key, map);
    }




    /**
* 
* Method: getRoc(String code) 
* 
*/ 
@Test
public void testGetRoc() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRona(String code) 
* 
*/ 
@Test
public void testGetRona() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getRota(String code) 
* 
*/ 
@Test
public void testGetRota() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getGm(String code) 
* 
*/ 
@Test
public void testGetGm() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getOm(String code) 
* 
*/ 
@Test
public void testGetOm() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getNpm(String code) 
* 
*/ 
@Test
public void testGetNpm() throws Exception { 
//TODO: Test goes here... 
} 


} 
