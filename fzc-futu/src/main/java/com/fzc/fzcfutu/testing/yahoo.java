package com.fzc.fzcfutu.testing;

import cn.hutool.json.JSONObject;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


public class yahoo {

    @Resource
    private RestTemplate restTemplate;









    public void something(){
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=IBM";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("get_product1返回结果：" + result);
        Assert.hasText(result, "get_product1返回结果为空");
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        String market = json.getStr("market");
        System.out.println("解析成功：" + market);
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        yahoo y = new yahoo();
        y.something();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }
}
