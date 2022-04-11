package com.fzc.fzcstocka.config;

import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.ResultCache;
import com.fzc.fzcstocka.util.ResultNameUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2022/3/1 14:15
 */
@Component
public class InitCacheInfoRunner implements ApplicationRunner {


    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Autowired
    private StockAInfoService stockAInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = "stockAInfo";
        Multimap<String,String> map = HashMultimap.create();


        Map<String, String> cacheMap = Maps.newHashMap();
        cacheMap.put("cf",ResultNameUtil.getCFId());
        cacheMap.put("mf",ResultNameUtil.getMFId());
        cacheMap.put("pr",ResultNameUtil.getPRId());
        cacheMap.put("ac",ResultNameUtil.getACId());
        cacheMap.put("is",ResultNameUtil.getISId());
        cacheMap.put("ls",ResultNameUtil.getLSId());
        cacheMap.put("ta",ResultNameUtil.getTAId());
//        ResultNameUtil.resultNameMap.put("cf",ResultNameUtil.getCFId());
//        ResultNameUtil.resultNameMap.put("mf",ResultNameUtil.getMFId());
//        ResultNameUtil.resultNameMap.put("pr",ResultNameUtil.getPRId());
//        ResultNameUtil.resultNameMap.put("ac",ResultNameUtil.getACId());
//        ResultNameUtil.resultNameMap.put("is",ResultNameUtil.getISId());

        ResultCache resultCache = ResultCache.getInstance();
        resultCache.cache.putAll(cacheMap);
//        LoadingCache<String, Graph> cache = CacheBuilder.newBuilder()
//                .maximumSize(10000)
//                .build(
//                        new CacheLoader<String,Graph>(){
//
//                            @Override
//                            public Graph load(String key) throws Exception {
//                                return createExpensiveGraph(key);
//                            }
//                        }
//                );
    }
}
