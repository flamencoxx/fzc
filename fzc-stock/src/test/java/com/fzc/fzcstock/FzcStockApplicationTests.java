package com.fzc.fzcstock;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstock.model.UserStock;
import com.fzc.fzcstock.service.UserStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FzcStockApplicationTests {

    @Autowired
    private UserStockService userStockService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void Test1(){
        UserStock userStock = new UserStock("IBM","摩根大通","fzc",300.34f);
        boolean result = userStockService.save(userStock);
        System.out.println("结果是:" + result);
    }
    @Test
    public void TestYahoo2(){
        String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=IBM";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("get_product1返回结果：" + result);
        Assert.hasText(result, "get_product1返回结果为空");
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        String market = json.getStr("market");
        System.out.println("解析成功：" + market);





    }

    @Test
    public void Tushare(){
        List<String> fields = new ArrayList<>();
        String url = "http://api.waditu.com";
        JSONObject params = JSONUtil.createObj()
                .put("ts_code","002603.SZ")
                .put("start_date","20190701")
                .put("end_date","");
        JSONObject json = JSONUtil.createObj()
                .put("api_name","daily")
                .put("token","8e17aab159f1840b1ad19ab1b602fb0fcf2bd7c48022109366291aeb")
                .put("params",params)
                .put("fields","");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json.toString(),headers);
        JSONObject result = restTemplate.postForObject(url,request,JSONObject.class);
        String a = result.toString();
//        System.out.println(a);
        JSONObject data = result.getJSONObject("data");
        JSONArray fieldsJson = (JSONArray)data.get("fields");
        for(int i = 0;i < fieldsJson.size(); i++){
            fields.add(fieldsJson.get(i).toString());
        }
        JSONArray it = (JSONArray)data.get("items");
        for (int i = 0;i < it.size();i++){
            JSONArray arr = (JSONArray) it.get(i);
            for (int j =0;j < arr.size();j++){
                System.out.println("第" +i+"队列："+fields.get(j) + "=" +arr.get(j).toString());
            }
        }
    }

}
