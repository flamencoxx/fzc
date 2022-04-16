package com.api;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.fzc.fzcstocka.DO.StockInfoDO;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.client.InfoTransformClient;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.ResultCache;
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

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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

    @Autowired
    private InfoTransformClient infoTransformClient;

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
    String res = infoTransformClient.getInfo();
    Console.log(res);
} 

/** 
* 
* Method: getRona(String code) 
* 
*/ 
@Test
public void testGetRona() throws Exception { 
//TODO: Test goes here...
    String code = "000001";
    StockInfoDO stockInfoDO = infoTransformClient.findStockInfo(code);
    Console.log(stockInfoDO);
    Console.log(stockInfoDO.getStockHistoricalDataList());
    List<StockInfoDO.StockHistoricalData> list = stockInfoDO.getStockHistoricalDataList();
    list.forEach(k->{
        Console.log(k.getClose());
    });
} 

/** 
* 
* Method: getRota(String code) 
* 
*/ 
@Test
public void testGetRota() throws Exception { 
//TODO: Test goes here...
    String code = "000001";
    String code2 = "000008";
    TimeInterval interval = new TimeInterval();
    StockInfoDO stockInfoDO = infoTransformClient.findStockInfo(code);
    Console.log(interval.intervalMs());
    StockInfoDO stockInfoDO2 = infoTransformClient.findStockInfo(code2);
    List<StockInfoDO.FinancialIndicators> list = stockInfoDO.getFinancialIndicatorsList();
    List<StockInfoDO.FinancialIndicators> list2 = stockInfoDO2.getFinancialIndicatorsList();
    Console.log(list.size());
    Console.log(list2.size());
} 

/** 
* 
* Method: getGm(String code) 
* 
*/ 
@Test
public void testGetGm() throws Exception { 
//TODO: Test goes here...
    ResultCache resultCache = ResultCache.getInstance();
    String code = "000001.SZSE";
    String res = resultCache.symbolCache.getIfPresent(code);
    Console.log(res);
} 

/** 
* 
* Method: getOm(String code) 
* 
*/ 
@Test
public void testGetOm() throws Exception { 
//TODO: Test goes here...
    String code = "601318.SSE";
    Future<StockInfoDO> future = stockAInfoService.AsyncGetStockInfoDO(code);
    ResultCache resultCache = ResultCache.getInstance();
    StockInfoDO stockInfoDO = resultCache.stockInfoCache.getIfPresent(code);
    Console.log(stockInfoDO);
    TimeInterval interval = new TimeInterval();
    StockInfoDO stockInfoDO1 = future.get();
    Console.log(interval.intervalMs());
//    resultCache.stockInfoCache.put(code, stockInfoDO1);
    Console.log(stockInfoDO1);
//    TimeInterval interval2 = new TimeInterval();
//    StockInfoDO stockInfoDO2 = resultCache.stockInfoCache.getIfPresent(code);
//    Console.log(interval2.intervalMs());
//    Console.log(stockInfoDO2);

//    Thread.sleep(3 * 60 * 1000);
    TimeInterval interval2 = new TimeInterval();
    StockInfoDO stockInfoDO2 = resultCache.stockInfoCache.getIfPresent(code);
    Console.log(interval2.intervalMs());
    Console.log(stockInfoDO2);
} 

/** 
* 
* Method: getNpm(String code) 
* 
*/ 
@Test
public void testGetNpm() throws Exception {
//TODO: Test goes here...

    String code = "601318.SSE";
    Future<StockInfoDO> future = stockAInfoService.AsyncGetStockInfoDO(code);
    StockInfoDO stockInfoDO = future.get();
    List<StockInfoDO.FinancialIndicators> list = stockInfoDO.getFinancialIndicatorsList();
    List<StockInfoDO.FinancialIndicators> list2 = list.stream().limit(30).sorted(Comparator.comparing(StockInfoDO.FinancialIndicators::getReportDate)).collect(Collectors.toList());
    list2.forEach(k->{
        Console.log(k.getReportDate() + ":" + k.getEps());
//        Console.log(k.getEps());
    });
}



    @Test
    public void testGetInfo() throws Exception {
//TODO: Test goes here...

        String code = "601318.SSE";
        String code1 = "000001.SZSE";
        PeerInfo peerInfo = factorApiService.getInfo(code1);
        Console.log(peerInfo);
    }



    @Test
    public void testnull() throws Exception {
//TODO: Test goes here...

        String code = "601318.SSE";
        String code1 = "000001.SZSE";
        ResultCache resultCache = ResultCache.getInstance();
        PeerInfo singlePeer = resultCache.singlePeerCache.getIfPresent(code);
        if(ObjectUtil.isNull(singlePeer) || ObjectUtil.isEmpty(singlePeer)){
            singlePeer = factorApiService.getInfo(code);
            resultCache.singlePeerCache.put(code, singlePeer);
        }
        PeerInfo singlePeer1 = resultCache.singlePeerCache.getIfPresent(code);
        Console.log(singlePeer1);
    }

} 
