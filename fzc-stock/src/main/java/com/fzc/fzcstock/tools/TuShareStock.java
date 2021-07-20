package com.fzc.fzcstock.tools;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11615
 */
public class TuShareStock {

    @Resource
    private RestTemplate restTemplate;

    private String code;
    public TuShareStock(){

    }
    public TuShareStock(String code){
        this.code=code;

    }

    public void daily(){
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
