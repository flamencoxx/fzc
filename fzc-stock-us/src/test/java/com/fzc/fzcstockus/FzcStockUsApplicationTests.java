package com.fzc.fzcstockus;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import com.fzc.fzcstockus.tool.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FzcStockUsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockUsInfoService stockUsageService;

    @Test
    public void insertStock(){
        List<String> fieldsList = new ArrayList<String>();
        String url = "http://api.waditu.com";
        JSONObject listStatus = JSONUtil.createObj()
                .put("offset","18000");
        JSONObject params = JSONUtil.createObj()
                .put("api_name","us_basic")
                .put("token","8e17aab159f1840b1ad19ab1b602fb0fcf2bd7c48022109366291aeb")
                .put("params",listStatus)
                .put("fields","ts_code,name,enname,classify,list_date,delist_date");
        String result = RestClient.postJson(url, params.toString());
        System.out.println(result);
        JSONObject json = JSONUtil.parseObj(result);
        JSONObject dataJson = json.getJSONObject("data");
        JSONArray fieldsJson = (JSONArray) dataJson.get("fields");
        JSONArray items = (JSONArray) dataJson.get("items");
        for (int i = 0;i < fieldsJson.size();i++){
            fieldsList.add(fieldsJson.get(i).toString());
            System.out.println("第：" + i +"个==" +fieldsJson.get(i).toString() );
        }

        for(int i = 0;i < items.size();i++){
            JSONArray single = (JSONArray) items.get(i);
            List<String> stockList = new ArrayList<String>();
            for(int j =0;j < single.size();j++){
                System.out.println("第：" + i +"个==" +fieldsList.get(j) +":"+ single.get(j).toString());
                stockList.add(single.get(j).toString());
            }
            StockUsInfo stock = new StockUsInfo(stockList.get(0),stockList.get(1),stockList.get(2),stockList.get(3),stockList.get(4),stockList.get(5));
            boolean r = stockUsageService.save(stock);
            System.out.println("结果是：" + r);
        }
    }

}
