package com.fzc.fzcstockus.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Flamenco.xxx
 * @date 2021/7/22 16:10
 */
@CrossOrigin()
@Controller
@Slf4j
@RequestMapping(value ="/AnalysisData")
public class StockAnalysisDataController {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

//    @RequestParam(value ="code")String code




    @GetMapping("update")
    public ResponseEntity<String> updateBasic(@RequestParam(value = "code")String symbol){
        stockBasicFinancialService.updateStockBasicFinancial(symbol);
        return ResponseEntity.ok("200");
    }


    @GetMapping("data")
    public ResponseEntity<JSONObject> getAnalysisData(@RequestParam(value ="code")String code){


//        log.info(code);
        log.info("收到前端传入的code:" + code);

        System.out.println("收到前端传入的code:" + code);

        JSONObject analysisJson = JSONUtil.createObj();



//        cardOne
        JSONObject cardOneJson = JSONUtil.createObj();
        cardOneJson.put("num",575);
        analysisJson.put("cardOne",cardOneJson);

//        cardTwo



//        cardThree



//        cardFour






//        visitData
        JSONArray visitDataJsonArray = JSONUtil.createArray();

        analysisJson.put("visitData",visitDataJsonArray);


//        visitData2
        JSONArray visitData2JsonArray = JSONUtil.createArray();

        analysisJson.put("visitData2",visitData2JsonArray);



//        salesData
        JSONArray salesDataJsonArray = JSONUtil.createArray();

        analysisJson.put("salesData",salesDataJsonArray);


//        SearchData
        JSONArray SearchDataJsonArray = JSONUtil.createArray();

        analysisJson.put("SearchData",SearchDataJsonArray);


//        offlineData

        JSONArray offlineDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineData",offlineDataJsonArray);

//        offlineChartData


        JSONArray offlineChartDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineChartData",offlineChartDataJsonArray);

//        salesTypeData

        JSONArray salesTypeDataJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeData",salesTypeDataJsonArray);


//        salesTypeDataOnline


        JSONArray salesTypeDataOnlineJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeDataOnline",salesTypeDataOnlineJsonArray);


//        salesTypeDataOffline


        JSONArray salesTypeDataOfflineJsonArray = JSONUtil.createArray();

        analysisJson.put("salesTypeDataOffline",salesTypeDataOfflineJsonArray);


//        radarData


        JSONArray radarDataJsonArray = JSONUtil.createArray();

        analysisJson.put("radarData",radarDataJsonArray);



        return ResponseEntity.ok(analysisJson);
    }
}
