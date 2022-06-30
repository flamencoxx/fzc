package com.fzc.fzcstocka.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fzc.fzcstocka.DO.StockInfoDO;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.model.ScoreInfo;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.ResultService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 */
@Component
public class ResultCache {

    @Autowired
    private ResultService resultService1;

    @Autowired
    private StockAInfoService stockAInfoService1;

    @Autowired
    private FactorApiService factorApiService1;

    private final static ResultCache INSTANCE = new ResultCache();

    private static ResultService resultService;

    private static StockAInfoService stockAInfoService;

    private static FactorApiService factorApiService;


    private ResultCache() {

    }

    @PostConstruct
    public void init() {
        resultService = resultService1;
        stockAInfoService = stockAInfoService1;
        factorApiService = factorApiService1;
    }

    public static ResultCache getInstance() {
        return INSTANCE;
    }

//    private static String getSomeThing(String code){
//        return resultService1.AsyncGetComprehensiveCode(code);
//    }

    public final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    String res = ResultNameUtil.getResultName(key);
                    if(StrUtil.isEmpty(res)) {
                        return "";
                    }
                    return res;
                }
            });


    public final  LoadingCache<String, ScoreInfo> comprehensiveCache = CacheBuilder.newBuilder()
            .maximumSize(3000)
            .expireAfterWrite(5, TimeUnit.HOURS)
            .recordStats()
            .build(new CacheLoader<String, ScoreInfo>() {
                @Override
                public ScoreInfo load(String key) throws Exception {
                    ScoreInfo res = resultService.getComprehensiveCode(key);
                    if(ObjectUtil.isEmpty(res)) {
                        return new ScoreInfo();
                    }
                    return res;
                }


            });


    public final LoadingCache<String, String> symbolCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(365, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<String,String>() {
                @Override
                public String load(String key) throws Exception {
                    return stockAInfoService.getSymbol(key);
                }
            });

    public final LoadingCache<String, StockInfoDO> stockInfoCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(3, TimeUnit.DAYS)
            .expireAfterAccess(3, TimeUnit.DAYS)
            .initialCapacity(2000)
            .recordStats()
            .build(new CacheLoader<String,StockInfoDO>() {
                @Override
                public StockInfoDO load(String key) throws Exception {
                    StockInfoDO stockInfoDO = stockAInfoService.getStockInfoDO(key);
                    if(ObjectUtil.isNull(stockInfoDO) || ObjectUtil.isEmpty(stockInfoDO)) {
                        return new StockInfoDO();
                    }
                    return stockInfoDO;
                }
            });

    public final LoadingCache<String,String> marketValueCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(3, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<String,String>() {
                @Override
                public String load(String key) throws Exception {
                    StockAInfo stockAInfo = stockAInfoService.findByStockIdentity(key);
                    return stockAInfo.getMarketValue();
                }
            });

    public final LoadingCache<String, PeerInfo> singlePeerCache = CacheBuilder.newBuilder()
            .maximumSize(300)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<String,PeerInfo>() {
                @Override
                public PeerInfo load(String key) throws Exception {
                    PeerInfo peerInfo = factorApiService.getInfo(key);
                    if(ObjectUtil.isNull(peerInfo) || ObjectUtil.isEmpty(peerInfo)) {
                        return new PeerInfo();
                    }
                    return peerInfo;
                }
            });

    public final LoadingCache<String,PeerInfo> peerListCache = CacheBuilder.newBuilder()
            .maximumSize(2000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<String,PeerInfo>() {
                @Override
                public PeerInfo load(String key) throws Exception {
                    PeerInfo peerInfo = factorApiService.getInfo(key);
                    if(ObjectUtil.isNull(peerInfo) || ObjectUtil.isEmpty(peerInfo)) {
                        return new PeerInfo();
                    }
                    return peerInfo;
                }
            });
}
