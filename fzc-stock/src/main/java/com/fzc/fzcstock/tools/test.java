package com.fzc.fzcstock.tools;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){


//        String jsonStr = "{\"name\":\"BeJson\",\"url\"}";
//        RestClient.postJson("http://www.baidu.com", jsonStr);


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
        String a = RestClient.postJson(url, json.toString());
        JSONObject result = JSONUtil.parseObj(a);
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
