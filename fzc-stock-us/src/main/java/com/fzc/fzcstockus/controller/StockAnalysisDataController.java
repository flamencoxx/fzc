package com.fzc.fzcstockus.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.AnalysisModel.SalesDataEmpty;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.PERatio;
import com.fzc.fzcstockus.model.PeList;
import com.fzc.fzcstockus.mutiThread.CallableFindStock;
import com.fzc.fzcstockus.producer.BasicFinancialProducer;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Flamenco.xxx
 * @date 2021/7/22 16:10
 */
@CrossOrigin()
@Controller
@Slf4j
@RequestMapping(value = "/AnalysisData")
public class StockAnalysisDataController {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockPeerService stockPeerService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private BasicFinancialProducer basicFinancialProducer;

//    @RequestParam(value ="code")String code


    @GetMapping("update")
    public ResponseEntity<String> updateBasic(@RequestParam(value = "code") String code) {
        stockBasicFinancialService.updateStockBasicFinancial(code);
        return ResponseEntity.ok("200");
    }


    @GetMapping("data")
    public ResponseEntity<JSONObject> getAnalysisData(@RequestParam(value = "code") String code) {

        long startTime = System.currentTimeMillis();

        if ("undefined".equals(code)) {
            code = "ibm";
        }

        //        创建线程池
//        ThreadPoolExecutor threadPool =
//                new ThreadPoolExecutor(6,10,60,
//                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));


        code = code.toUpperCase();


//        stockBasicFinancialService.updateStockBasicFinancial(code);


//        log.info(code);
        log.info("收到前端传入的code:" + code);

        boolean exit = stockUsInfoDoRepository.existsBySymbol(code);

        if (!exit) {
            JSONObject analysisJson = JSONUtil.createObj();
            JSONObject cardOneJson = JSONUtil.createObj();
            cardOneJson.put("num", 404);
            analysisJson.put("cardOne", cardOneJson);

            JSONArray visitDataJsonArray = JSONUtil.createArray();

            analysisJson.put("visitData", visitDataJsonArray);

            //        visitData2
            JSONArray visitData2JsonArray = JSONUtil.createArray();

            analysisJson.put("visitData2", visitData2JsonArray);
//        salesData
            JSONArray salesDataJsonArray = JSONUtil.createArray();
            analysisJson.put("salesData", salesDataJsonArray);
//        SearchData
            JSONArray SearchDataJsonArray = JSONUtil.createArray();
            analysisJson.put("SearchData", SearchDataJsonArray);
//        offlineData
            JSONArray offlineDataJsonArray = JSONUtil.createArray();
            analysisJson.put("offlineData", offlineDataJsonArray);
//        offlineChartData
            JSONArray offlineChartDataJsonArray = JSONUtil.createArray();
            analysisJson.put("offlineChartData", offlineChartDataJsonArray);
//        salesTypeData
            JSONArray salesTypeDataJsonArray = JSONUtil.createArray();
            analysisJson.put("salesTypeData", salesTypeDataJsonArray);
//        salesTypeDataOnline
            JSONArray salesTypeDataOnlineJsonArray = JSONUtil.createArray();
            analysisJson.put("salesTypeDataOnline", salesTypeDataOnlineJsonArray);
//        salesTypeDataOffline
            JSONArray salesTypeDataOfflineJsonArray = JSONUtil.createArray();
            analysisJson.put("salesTypeDataOffline", salesTypeDataOfflineJsonArray);

//        radarData

            JSONArray radarDataJsonArray = JSONUtil.createArray();

            analysisJson.put("radarData", radarDataJsonArray);

            return ResponseEntity.ok(analysisJson);
        }

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        CallableFindStock callableFindStock = new CallableFindStock();
//        callableFindStock.setSymbol(code);
//        Future<StockUsInfoDo> submit = executorService.submit(callableFindStock);
//
//        try {
//            StockUsInfoDo stock = submit.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code);
        List<StockUsInfoDo> peerList = stockPeerService.findPeerListBySymbolAsync(code);


//        try {
//            log.info("callable任务:"+submit.get().getSymbol());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
        log.info("收到前端传入的code:" + code);

        JSONObject analysisJson = JSONUtil.createObj();


//        cardOne
        JSONObject cardOneJson = JSONUtil.createObj();
        cardOneJson.put("num", 200);
        analysisJson.put("cardOne", cardOneJson);

//        cardTwo


//        cardThree


//        cardFour


//        rankingListData
        JSONArray rankingListDataJsonArray = JSONUtil.createArray();

        int peerLimit = 1;

//        List<StockUsInfoDo> peerList = stockPeerService.findPeerListBySymbol(code);
        for (StockUsInfoDo s : peerList) {

            JSONObject peerJson = JSONUtil.createObj();
            JSONObject peJson = JSONUtil.createObj();
            JSONArray peerArray = JSONUtil.createArray();

            if (peerLimit >= 9) {
                break;
            }
            peerLimit++;
            PeList peList = new PeList();
            String pe = s.getBasicFinancials().getMetric().getPeBasicExclExtraTTM();
            String roe = s.getBasicFinancials().getMetric().getRoeTTM();
            String revenueGross3Y = s.getBasicFinancials().getMetric().getRevenueGrowth3Y();
            String freeCashFlowPerShare = s.getBasicFinancials().getMetric().getFreeCashFlowPerShareTTM();
            String grossMarginAnnual = s.getBasicFinancials().getMetric().getGrossMarginAnnual();
            String netProfitMarginAnnual = s.getBasicFinancials().getMetric().getNetProfitMarginAnnual();

//            String[] head = pe.split("\\.");
//            char[] c = head[1].toCharArray();
//            String str1 = String.valueOf(c[0]) + c[1];
//            String str2 = head[0] +"."+ str1;

            peList.setSymbol(s.getSymbol());
            peList.setPe(pe);
            peList.setRoe(roe);
            peList.setRevenueGross3Y(revenueGross3Y);
            peList.setFreeCashFlowPerShare(freeCashFlowPerShare);
            peList.setGrossMarginAnnual(grossMarginAnnual);
            peList.setNetProfitMarginAnnual(netProfitMarginAnnual);

//            peJson.put("pe",pe);
//            peerArray.add(peJson);
//            peerJson.put("symbol",s.getSymbol());
//            peerArray.add(peerJson);
            rankingListDataJsonArray.add(peList);


        }

        log.info("返回pe列表");
        analysisJson.put("rankingListData", rankingListDataJsonArray);


//        visitData
        JSONArray visitDataJsonArray = JSONUtil.createArray();

        analysisJson.put("visitData", visitDataJsonArray);


//        visitData2
        JSONArray visitData2JsonArray = JSONUtil.createArray();

        analysisJson.put("visitData2", visitData2JsonArray);


//        有一些股票,例如aapl等因为股价日期和eps日期对不上没法算pe,以后再改将eps日期提前一天.
//        salesData
        JSONArray salesDataJsonArray = JSONUtil.createArray();
        List<PERatio> peList = stock.getPeRatios();
        Collections.reverse(peList);

        for (PERatio p : peList) {
            Formatter f = new Formatter();
            SalesDataEmpty s = new SalesDataEmpty();
            s.setX(p.getPeriod());
            String num = String.valueOf(f.format("%1.2f",Double.parseDouble(p.getV())));
            s.setY(Double.parseDouble(num));
            salesDataJsonArray.add(s);
        }


        analysisJson.put("salesData", salesDataJsonArray);


//        SearchData
        JSONArray SearchDataJsonArray = JSONUtil.createArray();

        analysisJson.put("SearchData", SearchDataJsonArray);


//        offlineData

        JSONArray offlineDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineData", offlineDataJsonArray);

//        offlineChartData


        JSONArray offlineChartDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineChartData", offlineChartDataJsonArray);

//        salesTypeData

        JSONArray salesTypeDataJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeData", salesTypeDataJsonArray);


//        salesTypeDataOnline


        JSONArray salesTypeDataOnlineJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeDataOnline", salesTypeDataOnlineJsonArray);


//        salesTypeDataOffline


        JSONArray salesTypeDataOfflineJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeDataOffline", salesTypeDataOfflineJsonArray);


//        radarData


        JSONArray radarDataJsonArray = JSONUtil.createArray();

        analysisJson.put("radarData", radarDataJsonArray);


        long endTime = System.currentTimeMillis();
        log.info(String.valueOf(endTime - startTime));

        return ResponseEntity.ok(analysisJson);
    }
}
