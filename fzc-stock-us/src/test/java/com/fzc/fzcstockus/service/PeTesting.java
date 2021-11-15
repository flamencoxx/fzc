package com.fzc.fzcstockus.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.Eps;
import com.fzc.fzcstockus.model.HistoryPrice;
import com.fzc.fzcstockus.model.PERatio;
import com.fzc.fzcstockus.model.StockClose;
import com.fzc.fzcstockus.repository.StockUsDORepository;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockPeerService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/25 11:09
 */
@SpringBootTest
public class PeTesting {
    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;

    @Autowired
    private StockUsHistoryDetialsService stockUsHistoryDetialsService;


    @Test
    public void PeTest() {
        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol.toUpperCase());
        List<Eps> epsList = stock.getBasicFinancials().getAnnual().getEps();
        List<String> dateList = new ArrayList<String>();
        for (Eps ep : epsList) {
            dateList.add(ep.getPeriod());
        }

//        System.out.println(epsList);
//        System.out.println(dateList);


        Long timeStamp = System.currentTimeMillis();
        String timeStampStr = timeStamp.toString().substring(0, 10);
        List<StockClose> stockHistoryList = new ArrayList<StockClose>();

        String url = "https://finnhub.io/api/v1/stock/candle?symbol=" + symbol.toUpperCase() + "&resolution=D&from=615298999&to=" + timeStampStr + "&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        List<Double> closeList = new ArrayList<Double>();
        List<String> timeList = new ArrayList<String>();
        JSONArray closeArray = (JSONArray) json.get("c");
        JSONArray timeArray = (JSONArray) json.get("t");


        for (Object s : closeArray) {
            closeList.add(Double.parseDouble(s.toString()));
        }

        for (Object s : timeArray) {
            timeList.add(s.toString());
        }


        for (int i = 0; i < timeList.size(); i++) {
            StockClose sc = new StockClose();
            Long timeLong = Long.parseLong(timeList.get(i)) * 1000L;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date(Long.parseLong(String.valueOf(timeLong))));
//            JSONArray singleArray = new JSONArray();
//            singleArray.add(time);
//            singleArray.add(closeList.get(i).toString());
            sc.setPeriod(time);
            sc.setClose(closeList.get(i).toString());
            stockHistoryList.add(sc);
        }

        System.out.println(stockHistoryList);


    }

    @Test
    public void database() {
        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<HistoryPrice> historyPriceList = stock.getHistoryPrices();
        System.out.println(historyPriceList);
    }

    @Test
    public void ServiceTesting() {
        String symbol = "aaaaaa";
        boolean result = stockUsHistoryDetialsService.fetchHistoryPrice(symbol);
        System.out.println(result);
    }


    @Test
    public void PeTest2() {
        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol.toUpperCase());
        List<Eps> epsList = stock.getBasicFinancials().getAnnual().getEps();
        List<String> dateList = new ArrayList<String>();
        for (Eps ep : epsList) {
            dateList.add(ep.getPeriod());
        }

        List<HistoryPrice> resultList = new ArrayList<HistoryPrice>();
        List<HistoryPrice> historyPriceList = stock.getHistoryPrices();

        for (String period : dateList) {
            for (HistoryPrice historyPrice : historyPriceList) {


                if (period.equals(historyPrice.getTime())) {
                    HistoryPrice hp = new HistoryPrice();
                    hp.setTime(historyPrice.getTime());
                    hp.setValue(historyPrice.getValue());
                    hp.setOpen(historyPrice.getOpen());
                    hp.setClose(historyPrice.getClose());
                    hp.setHigh(historyPrice.getHigh());
                    hp.setLow(historyPrice.getLow());
                    resultList.add(hp);
                }
            }
        }
        System.out.println(resultList);


    }

    @Test
    public void PeTest3() {
        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol.toUpperCase());
        List<Eps> epsList = stock.getBasicFinancials().getAnnual().getEps();
//        List<String> dateList = new ArrayList<String>();
//        for (Eps ep : epsList) {
//            dateList.add(ep.getPeriod());
//        }

        List<HistoryPrice> resultList = new ArrayList<HistoryPrice>();
        List<HistoryPrice> historyPriceList = stock.getHistoryPrices();
        List<PERatio> peRatioList = new ArrayList<PERatio>();

        for (Eps eps : epsList) {
            for (HistoryPrice historyPrice : historyPriceList) {


                if (eps.getPeriod().equals(historyPrice.getTime())) {
                    HistoryPrice hp = new HistoryPrice();
                    hp.setTime(historyPrice.getTime());
                    hp.setValue(historyPrice.getValue());
                    hp.setOpen(historyPrice.getOpen());
                    hp.setClose(historyPrice.getClose());
                    hp.setHigh(historyPrice.getHigh());
                    hp.setLow(historyPrice.getLow());
                    resultList.add(hp);


                    PERatio peRatio = new PERatio();
                    peRatio.setPeriod(historyPrice.getTime());
                    Double value = Double.parseDouble(historyPrice.getClose()) / Double.parseDouble(eps.getV());
                    String str = String.valueOf(value);
                    peRatio.setV(str);
                    peRatioList.add(peRatio);

                }
            }
        }
        stock.setPeRatios(peRatioList);
        stockUsInfoDoRepository.save(stock);
        System.out.println(resultList);


    }

    @Test
    public void test3(){
        for(int stockID = 1622;stockID < 4757;stockID++){
//            String symbol = "IBM";
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(stockID);


            List<Eps> epsList = new ArrayList<>();
            try {
                epsList = stock.getBasicFinancials().getAnnual().getEps();
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            }
//        List<String> dateList = new ArrayList<String>();
//        for (Eps ep : epsList) {
//            dateList.add(ep.getPeriod());
//        }

            List<HistoryPrice> resultList = new ArrayList<HistoryPrice>();
            List<HistoryPrice> historyPriceList = null;
            try {
                historyPriceList = stock.getHistoryPrices();
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            }
            List<PERatio> peRatioList = new ArrayList<PERatio>();



//            if(epsList.isEmpty()){
//                continue;
//            }
//            if(historyPriceList.isEmpty()){
//                continue;
//            }



            for (Eps eps : epsList) {
                for (HistoryPrice historyPrice : historyPriceList) {

                    try {
                        boolean bool = eps.getPeriod().equals(historyPrice.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }


                    if (eps.getPeriod().equals(historyPrice.getTime())) {
                        HistoryPrice hp = new HistoryPrice();
                        hp.setTime(historyPrice.getTime());
                        hp.setValue(historyPrice.getValue());
                        hp.setOpen(historyPrice.getOpen());
                        hp.setClose(historyPrice.getClose());
                        hp.setHigh(historyPrice.getHigh());
                        hp.setLow(historyPrice.getLow());
                        resultList.add(hp);


                        PERatio peRatio = new PERatio();
                        peRatio.setPeriod(historyPrice.getTime());
                        double value = Double.parseDouble(historyPrice.getClose()) / Double.parseDouble(eps.getV());
                        String str = String.valueOf(value);
                        peRatio.setV(str);
                        peRatioList.add(peRatio);

                    }
                }
            }
            stock.setPeRatios(peRatioList);
            stockUsInfoDoRepository.save(stock);
            System.out.println(stockID);
        }
    }

}
