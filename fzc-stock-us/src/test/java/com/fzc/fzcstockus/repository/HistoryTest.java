package com.fzc.fzcstockus.repository;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.model.HistoryPrice;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/6/24 20:18
 */
@SpringBootTest
public class HistoryTest {

    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockUsHistoryDetialsService stockUsHistoryDetialsService;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;


    @Test
    public void StockHistoryTest() {

        Long timeStamp = System.currentTimeMillis();
        String timeStampStr = timeStamp.toString().substring(0, 10);

        String url = "https://finnhub.io/api/v1/stock/candle?symbol=AAPL&resolution=D&from=1515298999&to=" + timeStampStr + "&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        List<Double> closeList = new ArrayList<Double>();
        List<Double> highList = new ArrayList<Double>();
        List<Double> lowList = new ArrayList<Double>();
        List<Double> openList = new ArrayList<Double>();
        List<String> timeList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();
        JSONArray closeArray = (JSONArray) json.get("c");
        JSONArray highArray = (JSONArray) json.get("h");
        JSONArray lowArray = (JSONArray) json.get("l");
        JSONArray openArray = (JSONArray) json.get("o");
        JSONArray timeArray = (JSONArray) json.get("t");
        JSONArray valueArray = (JSONArray) json.get("v");
        for (Object s : closeArray) {
            closeList.add(Double.parseDouble(s.toString()));
        }
        for (Object s : highArray) {
            highList.add(Double.parseDouble(s.toString()));
        }
        for (Object s : lowArray) {
            lowList.add(Double.parseDouble(s.toString()));
        }
        for (Object s : openArray) {
            openList.add(Double.parseDouble(s.toString()));
        }
        for (Object s : timeArray) {
            timeList.add(s.toString());
        }
        for (Object s : valueArray) {
            valueList.add(s.toString());
        }

        JSONObject dataJson = JSONUtil.createObj();
        JSONArray dataArray = new JSONArray();
        for (int i = 0; i < timeList.size(); i++) {
            Long timeLong = Long.parseLong(timeList.get(i)) * 1000L;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date(Long.parseLong(String.valueOf(timeLong))));
            JSONArray singleArray = new JSONArray();
            singleArray.add(time);
            singleArray.add(closeList.get(i).toString());
            singleArray.add(highList.get(i).toString());
            singleArray.add(lowList.get(i).toString());
            singleArray.add(openList.get(i).toString());
            singleArray.add(valueList.get(i).toString());
            dataArray.add(singleArray);
        }
        dataJson.put("data", dataArray);

//        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
//        System.out.println("格式化结果：" + sd);
//        System.out.println(timeStamp);

        System.out.println(dataJson.toString());


        String result = json.toString();
//        System.out.println(result);
    }

    @Test
    public void searchTest() {
        JSONObject json = stockUsInfoService.searchUsHistoryDay("IBM");
        System.out.println(json.toString());
    }

    @Test
    public void details() {
        JSONObject jsonObject = JSONUtil.createObj();

        String code = "IBM";

        StockUsInfoDo stockUsInfoDo = new StockUsInfoDo();
        BasicFinancials basicFinancials = new BasicFinancials();
        basicFinancials = stockBasicFinancialService.findBasicFinancialsBySymbol(code);
        stockUsInfoDo = stockCompanyInfoService.findStockCompanyInfo(code);
        String description = stockUsInfoDo.getCompanyOverview().getDescription();


        jsonObject.put("description", description);

        System.out.println(description);
    }

//    4757

    @Test
    public void fetchHistory() {

        for (int stockId = 207; stockId < 4757; stockId++) {
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(stockId);
            String symbol = stock.getSymbol();
            List<HistoryPrice> historyPrices = new ArrayList<HistoryPrice>();

            long timeStamp = System.currentTimeMillis();
            String timeStampStr = Long.toString(timeStamp).substring(0, 10);

            String url = "https://finnhub.io/api/v1/stock/candle?symbol=" + symbol + "&resolution=D&from=615298999&to=" + timeStampStr + "&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            List<Double> closeList = new ArrayList<Double>();
            List<Double> highList = new ArrayList<Double>();
            List<Double> lowList = new ArrayList<Double>();
            List<Double> openList = new ArrayList<Double>();
            List<String> timeList = new ArrayList<String>();
            List<String> valueList = new ArrayList<String>();
            JSONArray closeArray = (JSONArray) json.get("c");
            JSONArray highArray = (JSONArray) json.get("h");
            JSONArray lowArray = (JSONArray) json.get("l");
            JSONArray openArray = (JSONArray) json.get("o");
            JSONArray timeArray = (JSONArray) json.get("t");
            JSONArray valueArray = (JSONArray) json.get("v");

            try{
                for (Object s : closeArray) {
                    closeList.add(Double.parseDouble(s.toString()));
                }
            }catch(NullPointerException e){
                e.printStackTrace();
                continue;
            }


            for (Object s : highArray) {
                highList.add(Double.parseDouble(s.toString()));
            }
            for (Object s : lowArray) {
                lowList.add(Double.parseDouble(s.toString()));
            }
            for (Object s : openArray) {
                openList.add(Double.parseDouble(s.toString()));
            }
            for (Object s : timeArray) {
                timeList.add(s.toString());
            }
            for (Object s : valueArray) {
                valueList.add(s.toString());
            }


            for (int i = 0; i < timeList.size(); i++) {
                HistoryPrice historyPrice = new HistoryPrice();
                Long timeLong = Long.parseLong(timeList.get(i)) * 1000L;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdf.format(new Date(Long.parseLong(String.valueOf(timeLong))));

                historyPrice.setTime(time);
                historyPrice.setClose(closeList.get(i).toString());
                historyPrice.setOpen(openList.get(i).toString());
                historyPrice.setHigh(highList.get(i).toString());
                historyPrice.setLow(lowList.get(i).toString());
                historyPrice.setValue(valueList.get(i).toString());
                historyPrices.add(historyPrice);

            }

            stock.setHistoryPrices(historyPrices);
            stockUsInfoDoRepository.save(stock);
            System.out.println("现在是第"+stockId + "个:"+ stock.getSymbol());

            try {
                TimeUnit.MILLISECONDS.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
