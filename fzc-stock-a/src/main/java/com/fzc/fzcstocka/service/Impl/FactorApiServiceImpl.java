package com.fzc.fzcstocka.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author flamenco.xxx
 * @date 2022/2/28 11:16
 */
@Service
@Slf4j
public class FactorApiServiceImpl implements FactorApiService {

    public static final String HTTP_127_0_0_1_8383 = "http://127.0.0.1:8383/";
    public static final String GET_ROC = "get_ROC/";
    public static final String DATA = "data";
    public static final String GET_RONA = "get_RONA/";
    @Autowired
    private StockAInfoService stockAInfoService;



    @Override
    public String getRoc(String code) {
        String url = HTTP_127_0_0_1_8383 + GET_RONA + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        if(ObjectUtil.isNull(json)){
            return "";
        }else if(StrUtil.isBlank(json.getStr(DATA))){
            return "";
        }
        String resStr = json.getStr("result");
        return resStr;
    }

    @Override
    public String getRona(String code) {
        String url = HTTP_127_0_0_1_8383 + GET_ROC + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        if(ObjectUtil.isNull(json)){
            return "";
        }
        String resStr = json.getStr("result");
        return resStr;
    }

    @Override
    public String getRota(String code) {
        return null;
    }

    @Override
    public String getGm(String code) {
        return null;
    }

    @Override
    public String getOm(String code) {
        return null;
    }

    @Override
    public String getNpm(String code) {
        return null;
    }
}
