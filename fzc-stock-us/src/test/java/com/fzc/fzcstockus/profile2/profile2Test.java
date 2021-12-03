package com.fzc.fzcstockus.profile2;

import cn.hutool.json.JSONObject;
import com.fzc.fzcstockus.reportedModel.Data;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 9:55
 */
@SpringBootTest
public class profile2Test {

    @Test
    public void test(){
        String code = "aapl";
        String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ code +"&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
//        System.out.println(json);
        FinancialsReported financialsReported = new FinancialsReported();
        Data data = new Data();
        financialsReported = json.toBean(FinancialsReported.class);
        System.out.println(financialsReported.toString());
    }
}
