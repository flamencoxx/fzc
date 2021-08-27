package com.fzc.fzcstockus.servcie.Impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.HistoryPrice;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/8/9 17:35
 */
@Service
@Slf4j
public class StockUsHistoryDetialsServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockUsHistoryDetialsService {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;


    @Override
    public String showDescription(String code) {
        return null;
    }

    @Override
    public boolean fetchHistoryPrice(String symbol) {

        boolean result = false;

        boolean stockResult = stockUsInfoDoRepository.existsBySymbol(symbol);
        if (!stockResult){
            return false;
        }
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<HistoryPrice> historyPrices = new ArrayList<HistoryPrice>();

        long timeStamp = System.currentTimeMillis();
        String timeStampStr = Long.toString(timeStamp).substring(0, 10);

        String url = "https://finnhub.io/api/v1/stock/candle?symbol=" + symbol.toUpperCase() + "&resolution=D&from=615298999&to=" + timeStampStr + "&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();

        JSONObject json = restTemplate.getForObject(url, JSONObject.class);

        assert json != null;
        if (!json.isEmpty()) {
            result = true;
        }

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

        try {
            for (Object s : closeArray) {
                closeList.add(Double.parseDouble(s.toString()));
            }
        } catch (NullPointerException e) {
//            e.printStackTrace();
            return false;
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

        System.out.println("现在是:" + stock.getSymbol());

/*        try {
            TimeUnit.MILLISECONDS.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return result;
    }
}
