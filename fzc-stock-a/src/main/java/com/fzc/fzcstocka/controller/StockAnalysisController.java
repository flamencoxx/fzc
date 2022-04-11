package com.fzc.fzcstocka.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstocka.mapper.StockAInfoMapper;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.model.ScoreInfo;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.FactorPeerService;
import com.fzc.fzcstocka.service.ResultService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.ResultCache;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 15:34
 */
@CrossOrigin()
@Controller
@Slf4j
@RequestMapping(value = "/AnalysisData")
public class StockAnalysisController {


    public static final String UNDEFINED = "undefined";
    public static final String NONE = "None";
    @Autowired
    private ResultAnalyzerRepository resultDao;

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private StockAInfoMapper mapper;

    @Autowired
    private StockAInfoRepository repository;

    @Autowired
    private FactorPeerService factorPeerService;

    @Autowired
    private FactorApiService factorApiService;

    @Autowired
    private ResultService resultService;

    /**
     * 懒得拆分了,所以代码比较臃肿,别在意
     *
     * @param code
     * @return
     */
    @GetMapping("data")
    public ResponseEntity<JSONObject> getAnalysisData(String code) {
        long startTime = System.currentTimeMillis();

        if (UNDEFINED.equals(code)) {
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
            return ResponseEntity.ok(getNullJson());
        }


        //处理所有异步线程
        List<String> peerList = stockAInfoService.sortByValues(code);
        Future<Map<String, PeerInfo>> peerFuture = factorPeerService.AsyncGetPeer(peerList);

        Future<PeerInfo> singlePeerInfo = factorApiService.AsyncGetInfo(code);
        ResultCache resultCache = ResultCache.getInstance();
        ScoreInfo scoreInfo = resultCache.comprehensiveCache.getIfPresent(code);
        Future<ScoreInfo> futureScore = null;
        if (ObjectUtil.isEmpty(scoreInfo)) {
            futureScore = resultService.AsyncGetComprehensiveCode(code);
        }

        JSONObject analysisJson = JSONUtil.createObj();


//        cardOne
        JSONObject cardOneJson = JSONUtil.createObj();
        if (futureScore != null) {
            try {
                scoreInfo = futureScore.get();
                resultCache.comprehensiveCache.put(code, scoreInfo);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        if (StrUtil.isNotEmpty(scoreInfo.getComprehensiveScore())) {
            cardOneJson.put("totalScore", Integer.parseInt(scoreInfo.getComprehensiveScore()));
        }
        if (StrUtil.isNotEmpty(scoreInfo.getPassComprehensiveScore())) {
            int passScore = Integer.parseInt(scoreInfo.getPassComprehensiveScore());
            int totalScore = Integer.parseInt(scoreInfo.getComprehensiveScore());
            double passTotalScoreRate = ((double) totalScore / passScore - 1) * 100/100;
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumIntegerDigits(4);
            cardOneJson.put("passTotalScore", passScore);
            cardOneJson.put("passTotalScoreRate", nf.format(passTotalScoreRate));
        }
        analysisJson.put("cardOne", cardOneJson);

//        cardTwo


        JSONObject cardTwoJson = JSONUtil.createObj();
        ResultAnalyzer cfResultAnalyzer = scoreInfo.getCfResultAnalyzer();
        if(ObjectUtil.isNotEmpty(cfResultAnalyzer)){
            String cfScore = cfResultAnalyzer.getScore();
            String cfScoreRes = "";
            String cfReasonRes = "";
            if (StrUtil.isNotEmpty(cfScore) && !StrUtil.equals(cfScore, NONE) && StrUtil.isNotBlank(cfScore)) {
                cfScoreRes = cfResultAnalyzer.getScore();
            }
            cardTwoJson.put("cfScore", cfScoreRes);
            if (StrUtil.isNotEmpty(cfResultAnalyzer.getReason())) {
                cfReasonRes = cfResultAnalyzer.getReason();
            }
            cardTwoJson.put("cfReason", cfReasonRes);

        }else{
            cardTwoJson.put("cfScore", "不适用于此行业");
            cardTwoJson.put("cfReason", "不适用于此行业");
        }
        String gm = "";
        String om = "";
        String npm = "";
        PeerInfo peerInfo = null;
        try {
            peerInfo = singlePeerInfo.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if(ObjectUtil.isNotEmpty(peerInfo) && ObjectUtil.isNotNull(peerInfo)){
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumIntegerDigits(4);
            double gmDouble = Double.parseDouble(peerInfo.getGm()) * 100/100;
            double omDouble = Double.parseDouble(peerInfo.getOm()) * 100/100;
            double npmDouble = Double.parseDouble(peerInfo.getNpm()) * 100/100;
            gm = nf.format(gmDouble);
            om = nf.format(omDouble);
            npm = nf.format(npmDouble);
        }
        cardTwoJson.put("gm", gm);
        cardTwoJson.put("om", om);
        cardTwoJson.put("npm", npm);

        analysisJson.put("cardTwo", cardTwoJson);

//        cardThree


//        cardFour


//        rankingListData
        JSONArray rankingListDataJsonArray = JSONUtil.createArray();

        Map<String, PeerInfo> peerMap = Maps.newHashMap();
        try {
            peerMap = peerFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map<String, PeerInfo> finalPeerMap = peerMap;
        peerList.forEach(k -> {
            PeerInfo info = finalPeerMap.get(k);
            rankingListDataJsonArray.add(info);
        });
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

    public JSONObject getNullJson() {
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
        return analysisJson;
    }
}
