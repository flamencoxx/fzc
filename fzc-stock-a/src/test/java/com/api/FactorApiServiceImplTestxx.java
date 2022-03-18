package com.api;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.FactorPeerService;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.RestTemplateUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/** 
* FactorApiServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>3�� 7, 2022</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FzcStockAApplication.class)
public class FactorApiServiceImplTestxx {


    public static final String HTTP_127_0_0_1_8383 = "http://127.0.0.1:8383/";
//    资产收益率
    public static final String GET_ROC_PEER = "get_ROC_peer/";
//    净资产收益率
    public static final String GET_RONA_PEER = "get_RONA_peer/";
//    总资产收益率
    public static final String GET_ROTA_PEER = "get_ROTA_peer/";
//    毛利率
    public static final String GET_GM_PEER = "get_GM_peer/";
//    营业利润率
    public static final String GET_OM_PEER = "get_OM_peer/";
//    净利润率
    public static final String GET_NPM_PEER = "get_NPM_peer/";

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private StockAInfoRepository repository;

    @Autowired
    private FactorApiService factorApiService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FactorPeerService factorPeerService;

    public static final String STOCK_AINFO = "stockAInfo";
    @Value("${redis.database}")
    private String REDIS_DATABASE;



@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRoc(String code) 
* 
*/ 
@Test
public void testGetRoc() throws Exception { 
//TODO: Test goes here...
    String code = "000012.SZSE";
    TimeInterval interval = new TimeInterval();
    String res = factorApiService.getRoc(code);
    Console.log(res);
    Console.log(interval.interval());
}



    @Test
    public void test1() throws Exception {
//TODO: Test goes here...
        String url = HTTP_127_0_0_1_8383 + "get_ROC_peer/";
        JSONObject params = JSONUtil.createObj();
        JSONArray array = new JSONArray();
        List<String> list = Lists.newArrayList();
        list.add("test1");
        list.add("test2");
        list.add("test3");
//        array.addAll(list);
        array.add("test1");
        array.add("test2");
        array.add("test3");
        params.put("data",array);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(params.toString(), headers);



        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url,JSONObject.class,formEntity);
        Console.log(json.toString());
    }


    @Test
    public void test2() throws Exception {
//TODO: Test goes here...
        String url = HTTP_127_0_0_1_8383 + GET_GM_PEER;
        JSONObject params = JSONUtil.createObj();
        JSONArray array = new JSONArray();
        List<String> list = Lists.newArrayList();
        list.add("000001.SZSE");
        list.add("000006.SZSE");
        list.add("0000012.SZSE");
        String str = Joiner.on(",").join(list);
        url = url + str;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url,JSONObject.class);
        Console.log(json.toString());
    }


    /**
* 
* Method: getRona(String code) 
* 
*/ 
@Test
public void testGetRona() throws Exception { 
//TODO: Test goes here...
    List<String> list = Lists.newArrayList();
    list.add("000001.SZSE");
    list.add("000006.SZSE");
    list.add("000012.SZSE");
    list.add("000002.SZSE");
    list.add("000004.SZSE");
    list.add("000011.SZSE");
    list.add("000003.SZSE");
    list.add("000006.SZSE");
    list.add("000015.SZSE");
    TimeInterval timer = new TimeInterval();
    Map<String, PeerInfo> map = factorPeerService.getPeerInfoMap(list);
    Console.log(timer.interval());
    Console.log(map);
} 

/** 
* 
* Method: getRota(String code) 
* 
*/ 
@Test
public void testGetRota() throws Exception { 
//TODO: Test goes here...
    List<String> list = Lists.newArrayList();
    list.add("000001.SZSE");
    list.add("000006.SZSE");
    list.add("000012.SZSE");
    list.add("000002.SZSE");
    list.add("000004.SZSE");
    list.add("000011.SZSE");
    list.add("000003.SZSE");
    list.add("000006.SZSE");
    list.add("000015.SZSE");
    TimeInterval timer = new TimeInterval();
    Future<Map<String, PeerInfo>> future = factorPeerService.AsyncGetPeer(list);
    Future<Map<String, PeerInfo>> future1 = factorPeerService.AsyncGetPeer(list);
    Thread.sleep(200);
    Map<String, PeerInfo> map = future.get();
    Console.log(map);
    Console.log(timer.interval());
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
