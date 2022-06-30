package com.fzc.fzcstocka.controller;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstocka.DO.StockInfoDO;
import com.fzc.fzcstocka.mapper.StockAInfoMapper;
import com.fzc.fzcstocka.model.*;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import com.fzc.fzcstocka.service.*;
import com.fzc.fzcstocka.util.ResultCache;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 15:34
 * 项目中的核心部分
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

    @Autowired
    private FinanceBizCompositionService financeBizCompositionService;

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private final Lock lock3 = new ReentrantLock();
    private final Lock lock4 = new ReentrantLock();
    private final Lock lock5 = new ReentrantLock();
    private final Lock lock6 = new ReentrantLock();
    private final Lock lock7 = new ReentrantLock();
    private final Lock lock8 = new ReentrantLock();

    /**
     * 懒得拆分了,所以代码比较臃肿,别在意
     *
     * @param
     * @return
     */
    @GetMapping("data")
    public ResponseEntity<JSONObject> getAnalysisData(String code) {
        long startTime = System.currentTimeMillis();

        if (UNDEFINED.equals(code)) {
            code = "ibm";
        }
        //        创建线程池
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(5,
                        5,
                        60,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(100),
                        new DefaultThreadFactory("AnalysisThreadPool"),
                        new ThreadPoolExecutor.CallerRunsPolicy());
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
        ResultCache resultCache = ResultCache.getInstance();
        List<String> peerList = stockAInfoService.sortByValues(code);
        List<String> peerLost = Lists.newArrayList();
        Map<String, PeerInfo> finalPeerMap = Maps.newHashMap();
        peerList.forEach(k -> {
            PeerInfo peerSingle = resultCache.peerListCache.getIfPresent(k);
            if(ObjectUtil.isNull(peerSingle) || ObjectUtil.isEmpty(peerSingle)){
                peerLost.add(k);
            }else{
                finalPeerMap.put(k,peerSingle);
            }
        });
        Future<Map<String, PeerInfo>> peerFuture = factorPeerService.AsyncGetPeer(peerLost);
        PeerInfo singlePeer = resultCache.singlePeerCache.getIfPresent(code);
        if(singlePeer != null){
            Console.log("从缓存中获取单个股票的peer信息");
        }
        Future<PeerInfo> singlePeerInfo = new AsyncResult<>(new PeerInfo());
        if (ObjectUtil.isEmpty(singlePeer) ||ObjectUtil.isNull(singlePeer)) {
            singlePeerInfo = factorApiService.AsyncGetInfo(code);
        }

        Future<List<FinanceBusinessComposition>> compositionFuture = financeBizCompositionService.AsyncGetComposition(code);

        ScoreInfo scoreInfo = resultCache.comprehensiveCache.getIfPresent(code);
        StockInfoDO stockInfoDO = resultCache.stockInfoCache.getIfPresent(code);
        Future<ScoreInfo> futureScore = null;
        Future<StockInfoDO> futureStockInfoDO = null;
        if (ObjectUtil.isEmpty(scoreInfo)) {
            futureScore = resultService.AsyncGetComprehensiveCode(code);
        }
        if (ObjectUtil.isEmpty(stockInfoDO) || ObjectUtil.isNull(stockInfoDO)) {
            futureStockInfoDO = stockAInfoService.AsyncGetStockInfoDO(code);
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
            double passTotalScoreRate = ((double) totalScore / passScore - 1) * 100 / 100;
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumIntegerDigits(4);
            cardOneJson.put("passTotalScore", passScore);
            cardOneJson.put("passTotalScoreRate", nf.format(passTotalScoreRate));
        }
        analysisJson.put("cardOne", cardOneJson);

//        cardTwo

        JSONObject cardTwoJson = JSONUtil.createObj();
        ResultAnalyzer cfResultAnalyzer = scoreInfo.getCfResultAnalyzer();
        if (ObjectUtil.isNotEmpty(cfResultAnalyzer)) {
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

        } else {
            cardTwoJson.put("cfScore", "不适用于此行业");
            cardTwoJson.put("cfReason", "不适用于此行业");
        }
        String gm = "";
        String om = "";
        String npm = "";
        String roc = "";
        String rona = "";
        String rota = "";
        PeerInfo peerInfo = new PeerInfo();
        try {
            if (ObjectUtil.isNull(singlePeer) || ObjectUtil.isEmpty(singlePeer)){
                peerInfo = singlePeerInfo.get();
                resultCache.singlePeerCache.put(code, peerInfo);
            }else {
                peerInfo =  singlePeer;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ObjectUtil.isNotEmpty(peerInfo) && ObjectUtil.isNotNull(peerInfo)) {
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumIntegerDigits(4);
            double gmDouble = Double.parseDouble(peerInfo.getGm()) * 100 / 100;
            double omDouble = Double.parseDouble(peerInfo.getOm()) * 100 / 100;
            double npmDouble = Double.parseDouble(peerInfo.getNpm()) * 100 / 100;
            double rocDouble = Double.parseDouble(peerInfo.getRoc()) * 100 / 100;
            double ronaDouble = Double.parseDouble(peerInfo.getRona()) * 100 / 100;
            double rotaDouble = Double.parseDouble(peerInfo.getRota()) * 100 / 100;
            gm = nf.format(gmDouble);
            om = nf.format(omDouble);
            npm = nf.format(npmDouble);
            roc = nf.format(rocDouble);
            rona = nf.format(ronaDouble);
            rota = nf.format(rotaDouble);
        }
        cardTwoJson.put("gm", gm);
        cardTwoJson.put("om", om);
        cardTwoJson.put("npm", npm);

        analysisJson.put("cardTwo", cardTwoJson);

//        cardThree
        JSONObject cardThreeJson = JSONUtil.createObj();
        ResultAnalyzer mfResultAnalyzer = scoreInfo.getMfResultAnalyzer();
        if (ObjectUtil.isNotEmpty(cfResultAnalyzer)) {
            String mfScore = mfResultAnalyzer.getScore();
            String mfScoreReason = "";
            String mfScoreRes = "";
            if (StrUtil.isNotEmpty(mfScore) && !StrUtil.equals(mfScore, NONE) && StrUtil.isNotBlank(mfScore)) {
                mfScoreRes = mfScore;
            }
            cardThreeJson.put("mfScore", mfScoreRes);
            if (StrUtil.isNotEmpty(mfResultAnalyzer.getReason())) {
                mfScoreReason = mfResultAnalyzer.getReason();
            }
            cardThreeJson.put("mfScoreReason", mfScoreReason);
        } else {
            cardThreeJson.put("mfScore", "不适用于此行业");
            cardThreeJson.put("mfScoreReason", "不适用于此行业");
        }
        cardThreeJson.put("rota", rota);
        analysisJson.put("cardThree", cardThreeJson);

//        cardFour
        JSONObject cardFourJson = JSONUtil.createObj();
        ResultAnalyzer isResultAnalyzer = scoreInfo.getIsResultAnalyzer();
        if (ObjectUtil.isNotEmpty(cfResultAnalyzer)) {
            String isScore = isResultAnalyzer.getScore();
            String isScoreReason = "";
            String isScoreRes = "";
            if (StrUtil.isNotEmpty(isScore) && !StrUtil.equals(isScore, NONE) && StrUtil.isNotBlank(isScore)) {
                isScoreRes = isScore;
            }
            cardFourJson.put("isScore", isScoreRes);
            if (StrUtil.isNotEmpty(isResultAnalyzer.getReason())) {
                isScoreReason = isResultAnalyzer.getReason();
            }
            cardFourJson.put("isScoreReason", isScoreReason);
        } else {
            cardFourJson.put("isScore", "不适用于此行业");
            cardFourJson.put("isScoreReason", "不适用于此行业");
        }
        cardFourJson.put("roc", roc);
        cardFourJson.put("rona", rona);
        analysisJson.put("cardFour", cardFourJson);

//        cardFive
        JSONObject cardFiveJson = JSONUtil.createObj();


        analysisJson.put("cardFiveJson", cardFiveJson);

//        rankingListData
        JSONArray rankingListDataJsonArray = JSONUtil.createArray();

        Map<String, PeerInfo> peerMap = Maps.newHashMap();
        try {
            if(peerLost.size() != 0){
                peerMap = peerFuture.get();
                peerMap.entrySet().forEach(j ->{
                    resultCache.peerListCache.put(j.getKey(), j.getValue());
                    Console.log("添加了缓存: " + j.getKey());
                });
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        Map<String, PeerInfo> FPeerMap = peerMap;
        finalPeerMap.putAll(peerMap);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(4);
        peerList.forEach(k -> {
            PeerInfo info = finalPeerMap.get(k);
            JSONObject rankingListDataJson = JSONUtil.createObj();
            double rocNum = Double.parseDouble(info.getRoc()) * 100 / 100;
            double ronaNum = Double.parseDouble(info.getRona()) * 100 / 100;
            double rotaNum = Double.parseDouble(info.getRota()) * 100 / 100;
            double gmNum = Double.parseDouble(info.getGm()) * 100 / 100;
            double omNum = Double.parseDouble(info.getOm()) * 100 / 100;
            double npmNum = Double.parseDouble(info.getNpm()) * 100 / 100;
            rankingListDataJson.put("code", info.getCode());
            rankingListDataJson.put("roc", nf.format(rocNum));
            rankingListDataJson.put("rona", nf.format(ronaNum));
            rankingListDataJson.put("rota", nf.format(rotaNum));
            rankingListDataJson.put("gm", nf.format(gmNum));
            rankingListDataJson.put("om", nf.format(omNum));
            rankingListDataJson.put("npm", nf.format(npmNum));
            rankingListDataJsonArray.add(rankingListDataJson);
        });
        analysisJson.put("rankingListData", rankingListDataJsonArray);



//        visitData2
        JSONArray visitData2JsonArray = JSONUtil.createArray();
        List<ResAndPeriod> rocList = Lists.newArrayList();
        if (ObjectUtil.isNotEmpty(peerInfo) && ObjectUtil.isNotNull(peerInfo) && ObjectUtil.isNotEmpty(peerInfo.getRoc())) {
            rocList.addAll(peerInfo.getRocList());
        }
        if(!rocList.isEmpty()){
            rocList = rocList
                    .stream()
                    .limit(30)
                    .sorted(Comparator.comparing(ResAndPeriod::getPeriod))
                    .collect(Collectors.toList());
            rocList.forEach(k -> {
                JSONObject visitData2Json = JSONUtil.createObj();
                visitData2Json.put("x", k.getPeriod());
                visitData2Json.put("y", Double.parseDouble(k.getRes()));
                visitData2JsonArray.add(visitData2Json);
            });
        }
        analysisJson.put("visitData2", visitData2JsonArray);

//        visitData21
        JSONArray visitData21JsonArray = JSONUtil.createArray();

        List<ResAndPeriod> ronaList = Lists.newArrayList();
        if (ObjectUtil.isNotEmpty(peerInfo) && ObjectUtil.isNotNull(peerInfo) && ObjectUtil.isNotEmpty(peerInfo.getRoc())) {
            ronaList.addAll(peerInfo.getRonaList());
        }
        if(!ronaList.isEmpty()){
            ronaList = ronaList
                    .stream()
                    .limit(30)
                    .sorted(Comparator.comparing(ResAndPeriod::getPeriod))
                    .collect(Collectors.toList());
            ronaList.forEach(k -> {
                JSONObject visitData2Json = JSONUtil.createObj();
                visitData2Json.put("x", k.getPeriod());
                visitData2Json.put("y", Double.parseDouble(k.getRes()));
                visitData21JsonArray.add(visitData2Json);
            });
        }


        analysisJson.put("visitData21", visitData21JsonArray);

//        salesData
        JSONArray salesDataJsonArray = JSONUtil.createArray();

        if (ObjectUtil.isEmpty(stockInfoDO) || ObjectUtil.isNull(stockInfoDO)) {
            try {
                stockInfoDO = futureStockInfoDO.get();
                if ( ObjectUtil.isNull(stockInfoDO)) {
                    stockInfoDO = new StockInfoDO();
                }
                resultCache.stockInfoCache.put(code, stockInfoDO);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        List<String> reportDateList = Lists.newArrayList();
        Map<String,Double> epsMap = Maps.newHashMap();
        //主营业务收入
        Map<String,Long> rfbMap = Maps.newHashMap();
        //        营业利润
        Map<String,Long> pfbMap = Maps.newHashMap();
        //        净利润(扣除非经常性损益后)
        Map<String,Long> npaMap = Maps.newHashMap();
        //        经营活动产生的现金流量净额
        Map<String,Long> ncfMap = Maps.newHashMap();
        //        总资产
        Map<String,Long> taMap = Maps.newHashMap();
        //        总负债
        Map<String,Long> tlMap = Maps.newHashMap();
        //        每股净资产
        Map<String,Long> laMap = Maps.newHashMap();
        //        总利润
        Map<String,Long> tpMap = Maps.newHashMap();
        assert stockInfoDO != null;
        List<StockInfoDO.FinancialIndicators> financialIndicatorsList = stockInfoDO.getFinancialIndicatorsList();
        if (ObjectUtil.isNotEmpty(financialIndicatorsList)) {
            List<StockInfoDO.FinancialIndicators> financialIndicatorsList2 = financialIndicatorsList
                    .stream()
                    .limit(30)
                    .sorted(Comparator.comparing(StockInfoDO.FinancialIndicators::getReportDate))
                    .collect(Collectors.toList());
            financialIndicatorsList2.forEach(k -> {
                reportDateList.add(k.getReportDate());
                epsMap.put(k.getReportDate(),k.getEps());
                rfbMap.put(k.getReportDate(),k.getRevenueFromMainBusiness());
                pfbMap.put(k.getReportDate(),k.getProfitFromBusiness());
                npaMap.put(k.getReportDate(),k.getNetProfitAfter());
                ncfMap.put(k.getReportDate(),k.getNetCashFlowsFromOperatingActivities());
                taMap.put(k.getReportDate(),k.getTotalAssets());
                tlMap.put(k.getReportDate(),k.getTotalLiabilities());
                laMap.put(k.getReportDate(),k.getLiquidAssets());
                tpMap.put(k.getReportDate(), k.getTotalProfit());
            });
        }
        reportDateList.forEach(k -> {
            JSONObject salesDataJson = JSONUtil.createObj();
            salesDataJson.put("x", k);
            salesDataJson.put("y", epsMap.get(k));
            salesDataJsonArray.add(salesDataJson);
        });

        //        visitData
        JSONArray visitDataJsonArray = JSONUtil.createArray();


//        List<String> reportDateList2 = reportDateList
//                .stream()
//                .sorted(Comparator.reverseOrder())
//                .limit(20)
//                .collect(Collectors.toList());
//        Collections.reverse(reportDateList2);
//        reportDateList2.forEach(k -> {
//            JSONObject salesData1Json = JSONUtil.createObj();
//            salesData1Json.put("x", k);
//            salesData1Json.put("y", laMap.get(k));
//            visitDataJsonArray.add(salesData1Json);
//        });

        threadPool.execute(() -> {
            try {
                List<String> reportDateList2 = reportDateList
                        .stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(20)
                        .collect(Collectors.toList());
                Collections.reverse(reportDateList2);
                reportDateList2.forEach(k -> {
                    lock8.lock();
                    JSONObject salesData1Json = JSONUtil.createObj();
                    salesData1Json.put("x", k);
                    salesData1Json.put("y", laMap.get(k));
                    visitDataJsonArray.add(salesData1Json);
                    lock8.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });

        analysisJson.put("visitData", visitDataJsonArray);


        //        visitDataTp
        JSONArray visitDataJsonArrayTp = JSONUtil.createArray();


//        List<String> reportDateList21 = reportDateList
//                .stream()
//                .sorted(Comparator.reverseOrder())
//                .limit(20)
//                .collect(Collectors.toList());
//        Collections.reverse(reportDateList21);
//        reportDateList21.forEach(k -> {
//            JSONObject salesData1Json = JSONUtil.createObj();
//            salesData1Json.put("x", k);
//            salesData1Json.put("y", tpMap.get(k));
//            visitDataJsonArrayTp.add(salesData1Json);
//        });

        threadPool.execute(() -> {
            try {

                List<String> reportDateList2 = reportDateList
                        .stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(20)
                        .collect(Collectors.toList());
                Collections.reverse(reportDateList2);
                reportDateList2.forEach(k -> {
                    lock7.lock();
                    JSONObject salesData1Json = JSONUtil.createObj();
                    salesData1Json.put("x", k);
                    salesData1Json.put("y", tpMap.get(k));
                    visitDataJsonArrayTp.add(salesData1Json);
                    lock7.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });

        analysisJson.put("visitDataTp", visitDataJsonArrayTp);


        TimeInterval timeInterval = new TimeInterval();
//        salesData1
        JSONArray salesData1JsonArray = JSONUtil.createArray();
        threadPool.execute(() -> {
            try {

                reportDateList.forEach(k -> {
                    lock1.lock();
                    JSONObject salesData1Json = JSONUtil.createObj();
                    salesData1Json.put("x", k);
                    salesData1Json.put("y", tlMap.get(k));
                    salesData1JsonArray.add(salesData1Json);
                    lock1.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });

//        reportDateList.forEach(k -> {
//            JSONObject salesData1Json = JSONUtil.createObj();
//            salesData1Json.put("x", k);
//            salesData1Json.put("y", tlMap.get(k));
//            salesData1JsonArray.add(salesData1Json);
//        });


//        salesData2
        JSONArray salesData2JsonArray = JSONUtil.createArray();
        threadPool.execute((Runnable) () -> {
            try {
                reportDateList.forEach(k -> {
                    lock2.lock();
                    JSONObject salesDataJson = JSONUtil.createObj();
                    salesDataJson.put("x", k);
                    salesDataJson.put("y", rfbMap.get(k));
                    salesData2JsonArray.add(salesDataJson);
                    lock2.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });

//        reportDateList.forEach(k -> {
//            JSONObject salesDataJson = JSONUtil.createObj();
//            salesDataJson.put("x", k);
//            salesDataJson.put("y", rfbMap.get(k));
//            salesData2JsonArray.add(salesDataJson);
//        });


//        salesData3

        JSONArray salesData3JsonArray = JSONUtil.createArray();
        threadPool.execute((Runnable) () -> {
            try {
                reportDateList.forEach(k -> {
                    lock3.lock();
                    JSONObject salesDataJson = JSONUtil.createObj();
                    salesDataJson.put("x", k);
                    salesDataJson.put("y", pfbMap.get(k));
                    salesData3JsonArray.add(salesDataJson);
                    lock3.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        } );



//        reportDateList.forEach(k -> {
//            JSONObject salesDataJson = JSONUtil.createObj();
//            salesDataJson.put("x", k);
//            salesDataJson.put("y", pfbMap.get(k));
//            salesData3JsonArray.add(salesDataJson);
//        });


//        salesData4
        JSONArray salesData4JsonArray = JSONUtil.createArray();
        threadPool.execute((Runnable) () -> {
            try {
                reportDateList.forEach(k -> {
                    lock4.lock();
                    JSONObject salesDataJson = JSONUtil.createObj();
                    salesDataJson.put("x", k);
                    salesDataJson.put("y", npaMap.get(k));
                    salesData4JsonArray.add(salesDataJson);
                    lock4.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        } );

//        reportDateList.forEach(k -> {
//            JSONObject salesDataJson = JSONUtil.createObj();
//            salesDataJson.put("x", k);
//            salesDataJson.put("y", npaMap.get(k));
//            salesData4JsonArray.add(salesDataJson);
//        });


//        salesData5

        JSONArray salesData5JsonArray = JSONUtil.createArray();
        threadPool.execute((Runnable) () -> {
            try {

                reportDateList.forEach(k -> {
                    lock5.lock();
                    JSONObject salesDataJson = JSONUtil.createObj();
                    salesDataJson.put("x", k);
                    salesDataJson.put("y", ncfMap.get(k));
                    salesData5JsonArray.add(salesDataJson);
                    lock5.unlock();
                } );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });
//
//        reportDateList.forEach(k -> {
//            JSONObject salesDataJson = JSONUtil.createObj();
//            salesDataJson.put("x", k);
//            salesDataJson.put("y", ncfMap.get(k));
//            salesData5JsonArray.add(salesDataJson);
//        } );


//        salesData6

        JSONArray salesData6JsonArray = JSONUtil.createArray();
        threadPool.execute((Runnable) () -> {
            try {
                reportDateList.forEach(k -> {
                    lock6.lock();
                    JSONObject salesDataJson = JSONUtil.createObj();
                    salesDataJson.put("x", k);
                    salesDataJson.put("y", taMap.get(k));
                    salesData6JsonArray.add(salesDataJson);
                    lock6.unlock();
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        });


//        reportDateList.forEach(k -> {
//            JSONObject salesDataJson = JSONUtil.createObj();
//            salesDataJson.put("x", k);
//            salesDataJson.put("y", taMap.get(k));
//            salesData6JsonArray.add(salesDataJson);
//        });


        analysisJson.put("salesData", salesDataJsonArray);
        analysisJson.put("salesData1", salesData1JsonArray);
        analysisJson.put("salesData2", salesData2JsonArray);
        analysisJson.put("salesData3", salesData3JsonArray);
        analysisJson.put("salesData4", salesData4JsonArray);
        analysisJson.put("salesData5", salesData5JsonArray);
        analysisJson.put("salesData6", salesData6JsonArray);


//        SearchData

//        Set<String> periodSet = Sets.newHashSet();

//        Map<String,String> rotaMap = Maps.newHashMap();
//        Map<String,String> gmMap = Maps.newHashMap();
//        Map<String,String> omMap = Maps.newHashMap();
//        Map<String,String> npmMap = Maps.newHashMap();
//        Map<String,String> ldMap = Maps.newHashMap();


        JSONArray SearchDataJsonArray = JSONUtil.createArray();
        List<String> periodList = Lists.newArrayList();
        Map<String,Map<String,String>> searchMap = Maps.newHashMap();
        ImmutableSet<String> immutableSet = ImmutableSet.of("rotaList","gmList","omList","npmList","ldList");
        if(ObjectUtil.isNotEmpty(peerInfo) && ObjectUtil.isNotNull(peerInfo)){
            Class<?> clazz = peerInfo.getClass();
            PeerInfo finalPeerInfo = peerInfo;
            immutableSet.forEach(k ->{
                try {
                    searchMap.put(k,Maps.newHashMap());
                    Field field = clazz.getDeclaredField(k);
                    try {
                        field.setAccessible(true);
                        Object obj = field.get(finalPeerInfo);
                        if (obj instanceof ArrayList<?>) {
                            List<?> resAndPeriodList = (List<?>)field.get(finalPeerInfo);
                            boolean finalSetStatus = ObjectUtil.isEmpty(periodList) && periodList.size() == 0;
                            Map<String,String> tempMap = searchMap.get(k);
                            resAndPeriodList.forEach(j ->{
                                if(j instanceof ResAndPeriod){
                                    ResAndPeriod resAndPeriod = (ResAndPeriod)j;
                                    if(finalSetStatus){
                                        periodList.add(resAndPeriod.getPeriod());
                                    }
                                    tempMap.put(resAndPeriod.getPeriod(),resAndPeriod.getRes());
                                    field.setAccessible(false);
                                }
                            });
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            });
            AtomicInteger index = new AtomicInteger(1);
            NumberFormat nf1 = NumberFormat.getPercentInstance();
            nf1.setMinimumFractionDigits(2);
            periodList.forEach(k ->{
                JSONObject searchDataJson = JSONUtil.createObj();
                immutableSet.forEach(j ->{
                    if(StrUtil.isBlank(searchMap.get(j).get(k))){
                        searchDataJson.put(j,"");
                        return;
                    }
                    double res = Double.parseDouble(searchMap.get(j).get(k))* 10000/10000;
                    searchDataJson.put(j,nf1.format(res));
                });
                searchDataJson.put("period",k);
                searchDataJson.put("index", index.getAndIncrement());
                SearchDataJsonArray.add(searchDataJson);
            });
        }

        analysisJson.put("searchData", SearchDataJsonArray);


//        offlineData

        JSONArray offlineDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineData", offlineDataJsonArray);

//        offlineChartData


        JSONArray offlineChartDataJsonArray = JSONUtil.createArray();

        analysisJson.put("offlineChartData", offlineChartDataJsonArray);

//        salesTypeData

        JSONArray salesTypeDataJsonArray = JSONUtil.createArray();

        Set<String> peerSet = finalPeerMap.keySet();
        peerSet.forEach(k -> {
            try {
                JSONObject salesDataJson = JSONUtil.createObj();
                String marketStr = resultCache.marketValueCache.get(k);
                double marketValueLong = Double.parseDouble(marketStr) / 1000000000;
                salesDataJson.put("x", k);
                salesDataJson.put("y", marketValueLong);
                salesTypeDataJsonArray.add(salesDataJson);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } );

        analysisJson.put("salesTypeData", salesTypeDataJsonArray);


//        salesTypeDataOnline
        JSONArray salesTypeDataOnlineJsonArray = JSONUtil.createArray();
        List<FinanceBusinessComposition> compositionList = Lists.newArrayList();
        try {
            compositionList = compositionFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        if (compositionList != null && compositionList.size() != 0) {
            Map<String,Double> productMap = Maps.newHashMap();
            List<FinanceBusinessComposition> productList = compositionList
                    .stream()
                    .filter(k -> "product".equals(k.getClassification()))
                    .sorted(Comparator.comparing(FinanceBusinessComposition::getPeriod).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            Set<String> itemSet = productList
                    .stream()
                    .map(FinanceBusinessComposition::getBzItem)
                    .collect(Collectors.toSet());
            itemSet.forEach(k->{
               List<FinanceBusinessComposition> singleProductList = productList
                       .stream()
                       .filter(o -> k.equals(o.getBzItem()))
                       .sorted(Comparator.comparing(FinanceBusinessComposition::getPeriod).reversed())
                       .collect(Collectors.toList());
//                System.out.println(singleProductList);
                productMap.put(k,(double)(singleProductList.get(0).getBzSales())/100000000);
            });

            itemSet.forEach(k -> {
                JSONObject salesDataJson = JSONUtil.createObj();
                salesDataJson.put("x", k);
                salesDataJson.put("y", productMap.get(k));
                salesTypeDataOnlineJsonArray.add(salesDataJson);
            });
        }




        analysisJson.put("salesTypeDataOnline", salesTypeDataOnlineJsonArray);


//        salesTypeDataOffline


        JSONArray salesTypeDataOfflineJsonArray = JSONUtil.createArray();

        if (compositionList != null && compositionList.size() != 0) {
            Map<String,Double> productMap = Maps.newHashMap();
            List<FinanceBusinessComposition> productList = compositionList
                    .stream()
                    .filter(k -> "area".equals(k.getClassification()))
                    .sorted(Comparator.comparing(FinanceBusinessComposition::getPeriod).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            Set<String> itemSet = productList
                    .stream()
                    .map(FinanceBusinessComposition::getBzItem)
                    .collect(Collectors.toSet());
            itemSet.forEach(k->{
                List<FinanceBusinessComposition> singleProductList = productList
                        .stream()
                        .filter(o -> k.equals(o.getBzItem()))
                        .sorted(Comparator.comparing(FinanceBusinessComposition::getPeriod).reversed())
                        .collect(Collectors.toList());
//                System.out.println(singleProductList);
                productMap.put(k,(double)singleProductList.get(0).getBzSales()/ 100000000);
            });

            itemSet.forEach(k -> {
                JSONObject salesDataJson = JSONUtil.createObj();
                salesDataJson.put("x", k);
                salesDataJson.put("y", productMap.get(k));
                salesTypeDataOfflineJsonArray.add(salesDataJson);
            });
        }


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
