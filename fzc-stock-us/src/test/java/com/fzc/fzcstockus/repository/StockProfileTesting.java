package com.fzc.fzcstockus.repository;

import cn.hutool.json.JSONObject;
import com.alibaba.nacos.common.utils.ThreadUtils;
import com.fzc.fzcstockus.model.StockProfile2;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.servcie.StockProfile2Service;
import com.fzc.fzcstockus.servcie.StockUsImportService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import com.fzc.fzcstockus.util.ImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/12/1 10:29
 */
@SpringBootTest
public class StockProfileTesting {

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockProfile2Service stockProfile2Service;


    @Test
    public void test() {
        for (int i = 1; i < 4700; i++) {

        }
        StockUsImport stockUsImport = new StockUsImport();
        String code = "ibm";
        String url = "https://finnhub.io/api/v1/stock/profile2?symbol=" +
                code + "&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        StockProfile2 stockProfile2 = new StockProfile2();
        String symbol = json.getStr("ticker");
        String country = json.getStr("country");
        String currency = json.getStr("currency");
        String exchange = json.getStr("exchange");
        String finnhubIndustry = json.getStr("finnhubIndustry");
        String ipo = json.getStr("ipo");
        String shareOutstanding = json.getStr("shareOutstanding");
    }

    @Test
    public void test2() {
        for (int i = 3513; i < 4700; i++) {
            StockUsImport stockUsImport = stockUsImportService.getById(i);
            String code = stockUsImport.getSymbol();
            String url = "https://finnhub.io/api/v1/stock/profile2?symbol=" +
                    code + "&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            StockProfile2 stockProfile2 = new StockProfile2();
            if (json != null) {
                stockProfile2 = json.toBean(StockProfile2.class);
            }
            stockProfile2.setSymbol(stockUsImport.getSymbol());
            System.out.println(stockProfile2.toString());
            stockProfile2Service.save(stockProfile2);
            ThreadUtils.sleep(1000);
        }
    }

    @Test
    public void init() {
        List<StockUsImport> imports = stockUsImportService.list();
        imports.forEach(k -> {
            String code = k.getSymbol();
            String url = "https://finnhub.io/api/v1/stock/profile2?symbol=" +
                    code + "&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            StockProfile2 stockProfile2 = new StockProfile2();
            if (json != null) {
                stockProfile2 = json.toBean(StockProfile2.class);
            }
            stockProfile2.setSymbol(k.getSymbol());
//            stockProfile2.setId(k.getId());
            System.out.println(stockProfile2.toString());
            stockProfile2Service.save(stockProfile2);
            ThreadUtils.sleep(1000);
        });
    }

    @Test
    public void imagesTest() {
        String url = "https://finnhub.io/api/logo?symbol=IBER";
        byte[] images = ImageUtil.getImageFromNetByUrl(url);
        String s = Arrays.toString(images);
    }

    @Test
    public void writeToTest() {
        List<StockUsImport> list = stockUsImportService.list();
        list.forEach(k -> {
            String code = k.getSymbol();
            String url = "https://finnhub.io/api/logo?symbol=" + code;
            byte[] images = ImageUtil.getImageFromNetByUrl(url);
            String fileName = code + ".png";
            ImageUtil.writeImageToDisk(images,fileName);
        });
    }
}
