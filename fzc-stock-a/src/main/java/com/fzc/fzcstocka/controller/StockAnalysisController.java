package com.fzc.fzcstocka.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstocka.mapper.StockAInfoMapper;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.StockAInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 15:34
 */
@CrossOrigin()
@Controller
@Slf4j
@RequestMapping(value = "/AnalysisData")
public class StockAnalysisController {


    @Autowired
    private ResultAnalyzerRepository resultDao;

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private StockAInfoMapper mapper;

    @Autowired
    private StockAInfoRepository repository;

    /**
     * 懒得拆分了,所以代码比较臃肿,别在意
     * @param code
     * @return
     */
    @GetMapping("data")
    public ResponseEntity<JSONObject> getAnalysisData(String code){
        long startTime = System.currentTimeMillis();

        if ("undefined".equals(code)) {
            code = "ibm";
        }
        //        创建线程池
//        ThreadPoolExecutor threadPool =
//                new ThreadPoolExecutor(6,10,60,
//                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));
        code = code.toUpperCase();

        log.info("收到前端传入的code:" + code);
        boolean exit = repository.existsByCodeOrSymbolOrStockIdentityOrTsCode(
                code,
                code,
                code,
                code);
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
            JSONArray searchDataJsonArray = JSONUtil.createArray();
            analysisJson.put("SearchData", searchDataJsonArray);
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
