package com.api;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcstocka.FzcStockAApplication;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.RestTemplateUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FactorApiServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2�� 28, 2022</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FzcStockAApplication.class)
public class FactorApiServiceImplTest {


    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private StockAInfoRepository repository;

    @Autowired
    private FactorApiService factorApiService;

    @Autowired
    private RedisService redisService;

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
     * Method: getRoc(String code)
     */
    @Test
    public void testGetRoc() throws Exception {
//TODO: Test goes here...
        String code = "000333.SZSE";
        String url = "http://127.0.0.1:8383/get_ROC/" + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        assert json != null;
        Console.log(json.toString());
    }

    @Test
    public void test1() throws Exception {
//TODO: Test goes here...
        String code = "000333.SZSE";
        String code1 = "sss";
        String res = factorApiService.getRoc(code1);
        Console.log(res);
    }


    @Test
    public void test2() throws Exception {
//TODO: Test goes here...
        String api_name = "daily_basic";
        String token = "2cd7c1c0caf424ccbbdfad489ee875aaf02fb6565fc8a0e0ca0cae65";
        String code = "000780.SZ";
        String url = "http://api.tushare.pro";
        JSONObject params = new JSONObject();
        params.put("ts_code", code);
        JSONObject requestJson = JSONUtil.createObj();
        requestJson.put("api_name", api_name);
        requestJson.put("token", token);
        requestJson.put("params", params);
        requestJson.put("fields", "");
        Console.log(requestJson.toString());
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.postForObject(url, requestJson, JSONObject.class);
//        Console.log(json.toString());
        Console.log(formatToJson(json.toString()));
    }


    @Test
    public void test3() throws Exception {
//TODO: Test goes here...
        List<StockAInfo> list = stockAInfoService.list();
        list.forEach(k -> {
            try {
                String marketValue = getMarketValue(k.getTsCode());
                Console.log(marketValue);
                k.setMarketValue(marketValue);
                boolean bool = stockAInfoService.saveOrUpdate(k);
                Console.log(k.getTsCode() + ":" + bool);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void test4() throws Exception {
//TODO: Test goes here...
        String code = "000001.sz";
        String res = getMarketValue(code);
        Console.log(res);
    }

    @Test
    public void test5() throws Exception {
//TODO: Test goes here...
        List<StockAInfo> list = stockAInfoService.list();
        list.forEach(k -> {
            String res = split(k.getMarketValue());
            k.setMarketValue(res);
            boolean bool = stockAInfoService.saveOrUpdate(k);
            Console.log(k.getTsCode() + ":" + bool);
        });
    }

    @Test
    public void test6() throws Exception {
//TODO: Test goes here...
        String code = "000012.SZSE";
        QueryWrapper<StockAInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stock_identity", code);
        StockAInfo stock = stockAInfoService.getOne(queryWrapper);
        List<StockAInfo> list = stockAInfoService.list();
        Map<String, Long> map = new HashMap<>();
        TimeInterval timer = new TimeInterval();
        list.forEach(k -> {
            map.put(k.getStockIdentity(), Long.parseLong(k.getMarketValue()));
        });
        Console.log(timer.interval());
    }

    @Test
    public void test7() throws Exception {
//TODO: Test goes here...
        String code = "000012.SZSE";
        List<StockAInfo> list = stockAInfoService.searchIndustryList(code);
        Console.log(list.size());
        Map<String, Long> map = Maps.newHashMap();
        list.forEach(k -> {
//            Console.log(k.getStockIdentity() + "==" + k.getMarketValue());
            map.put(k.getStockIdentity(), Long.parseLong(k.getMarketValue()));
        });
        List<Map.Entry<String, Long>> entryList = Lists.newArrayList(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        entryList.forEach(Console::log);
        List<String> resList = entryList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        Collections.reverse(resList);
        resList.forEach(Console::log);
    }

    @Test
    public void test8() throws Exception {
        String code = "000012.SZSE";
        TimeInterval timer = new TimeInterval();
        List<String> list = stockAInfoService.sortByValues(code);
        Console.log(timer.interval());
        list.forEach(Console::log);
    }

    @Test
    public void test9() throws Exception {
        Console.log("test");
        String code = "000012.SZSE";
        TimeInterval timer = new TimeInterval();
        StockAInfo stock = stockAInfoService.findByStockIdentity(code);
        Console.log(stock.toString());
        Console.log(timer.interval());

        TimeInterval timer2 = new TimeInterval();
        String key = REDIS_DATABASE + ":" + STOCK_AINFO;
        StockAInfo stock2 = (StockAInfo) redisService.hGet(key, code);
        Console.log(stock2.toString());
        Console.log(timer2.interval());
    }


    public static String split(String str) {
        List<String> array = Splitter.on(".").splitToList(str);
        return array.get(0);
    }

    public static String getMarketValue(String code) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String end = formatter.format(date);
        DateTime datetime = DateUtil.lastWeek();
        String start = formatter.format(datetime);
        String api_name = "daily_basic";
        String token = "2cd7c1c0caf424ccbbdfad489ee875aaf02fb6565fc8a0e0ca0cae65";
        String url = "http://api.tushare.pro";
        JSONObject params = new JSONObject();
        params.put("ts_code", code);
        params.put("start_date", start);
        params.put("end_date", end);
        JSONObject requestJson = JSONUtil.createObj();
        requestJson.put("api_name", api_name);
        requestJson.put("token", token);
        requestJson.put("params", params);
        requestJson.put("fields", "");
        Console.log(requestJson.toString());
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.postForObject(url, requestJson, JSONObject.class);
//        Console.log(json.toString());
//        Console.log(formatToJson(json.toString()));
        JSONObject dataJson = (JSONObject) json.get("data");
        JSONArray array = (JSONArray) dataJson.get("items");
        JSONArray resArray = (JSONArray) array.get(0);

        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
        nf.setGroupingUsed(false);
// 设置数的小数部分所允许的最小位数
        nf.setMinimumFractionDigits(0);
// 设置数的小数部分所允许的最大位数
        nf.setMaximumFractionDigits(5);

        Double marketValue = (Double) resArray.get(16) * 10000;
        String res = nf.format(marketValue);
        return res;
    }

    public static String formatToJson(String region) {
        int level = 0;
        StringBuffer preBuffer = new StringBuffer();
        for (int i = 0; i < region.length(); i++) {
            char c = region.charAt(i);
            if (level > 0 && '\n' == preBuffer.charAt(preBuffer.length() - 1)) {
                preBuffer.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    preBuffer.append(c + "\n");
                    level++;
                    break;
                case ',':
                    preBuffer.append(c + "\n");
                    break;
                case '}':
                case ']':
                    preBuffer.append("\n");
                    level--;
                    preBuffer.append(getLevelStr(level));
                    preBuffer.append(c);
                    break;
                default:
                    preBuffer.append(c);
                    break;
            }
        }

        return String.valueOf(preBuffer);

    }

    private static String getLevelStr(int level) {
        StringBuffer lb = new StringBuffer();
        for (int levelTmp = 0; levelTmp < level; levelTmp++) {
            lb.append("\t");
        }
        return String.valueOf(lb);
    }


} 
