package com.fzc.fzcstocka.config;

import cn.hutool.core.lang.Console;
import com.fzc.fzcstocka.DO.StockInfoDO;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.ResultCache;
import com.fzc.fzcstocka.util.ResultNameUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author flamenco.xxx
 * @date 2022/3/1 14:15
 */
@Component
@Slf4j
public class InitCacheInfoRunner implements ApplicationRunner {


    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Autowired
    private StockAInfoService stockAInfoService;

    private int getMb(Object obj) {

        if (obj == null) {
            return 0;
        }
        //计算指定对象本身在堆空间的大小，单位字节
        long byteCount = RamUsageEstimator.shallowSizeOf(obj);
        if (byteCount == 0) {
            return 0;
        }
        double oneMb = 1 * 1024 * 1024;

        if (byteCount < oneMb) {
            return 1;
        }

        Double v = Double.valueOf(byteCount) / oneMb;
        return v.intValue();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = "stockAInfo";
        Multimap<String, String> map = HashMultimap.create();

        Map<String,StockInfoDO> stockInfoMap = Maps.newHashMap();

        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(12,
                        24,
                        60,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(100),
                        new DefaultThreadFactory("initThread"),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        Set<String> set = stockAInfoService.getBigMarketValueStock();
        List<Future<StockInfoDO>> futureList = Lists.newArrayList();
        List<StockInfoDO> resultList = Lists.newArrayList();
        ResultCache resultCache = ResultCache.getInstance();


        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        boolean symbolUpdate = stockAInfoService.updateSymbolCache();

        if (symbolUpdate) {
            log.info("刷新symbol数据成功");
        }

        threadPool.execute(() -> {
            try {
                List<StockAInfo> list = stockAInfoService.list();
                list.forEach(k ->{
                    resultCache.marketValueCache.put(k.getStockIdentity(),k.getMarketValue());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );

        set.forEach(k -> {
            futureList.add(threadPool.submit(() -> {
//                Console.log("开始获取{}", k);
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return stockAInfoService.getStockInfoDO(k);
            }));
        });

        futureList.forEach(k ->{
            try {
                if(k.get() != null){
                    resultList.add(k.get());
                    log.info("获取{}成功", k.get().getCode());
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("数据获取异常:", e);
                e.printStackTrace();
            }
        });

        resultList.forEach(k ->{
            stockInfoMap.put(k.getSymbol(),k);
//            Console.log("map获取{}成功：{}", k.getCode(),k.getIndustry());
        });


        long size = RamUsageEstimator.sizeOfObject(resultList);
        int mbSize = getMb(resultList);
        Console.log("size:{}", size);
        Console.log("mbSize:{}", mbSize);
        set.forEach(k ->{
            try {
                String symbol = resultCache.symbolCache.get(k);
                if(stockInfoMap.containsKey(symbol)){
                    StockInfoDO stockInfo = stockInfoMap.get(symbol);
                    resultCache.stockInfoCache.put(k,stockInfo);
//                    log.info("缓存股票 {} 信息:{}",k,stockInfo);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

//        set.forEach(k -> {
//            threadPool.execute(() -> {
//                StockInfoDO stock = stockAInfoService.getStockInfoDO(k);
//                ResultCache resultCache = ResultCache.getInstance();
//                resultCache.stockInfoCache.put(k, stock);
//                log.info("股票信息缓存成功：{} ---- {}", k, stock);
//                Console.log("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//            });
//        });


        Map<String, String> cacheMap = Maps.newHashMap();
        cacheMap.put("cf", ResultNameUtil.getCFId());
        cacheMap.put("mf", ResultNameUtil.getMFId());
        cacheMap.put("pr", ResultNameUtil.getPRId());
        cacheMap.put("ac", ResultNameUtil.getACId());
        cacheMap.put("is", ResultNameUtil.getISId());
        cacheMap.put("ls", ResultNameUtil.getLSId());
        cacheMap.put("ta", ResultNameUtil.getTAId());
//        ResultNameUtil.resultNameMap.put("cf",ResultNameUtil.getCFId());
//        ResultNameUtil.resultNameMap.put("mf",ResultNameUtil.getMFId());
//        ResultNameUtil.resultNameMap.put("pr",ResultNameUtil.getPRId());
//        ResultNameUtil.resultNameMap.put("ac",ResultNameUtil.getACId());
//        ResultNameUtil.resultNameMap.put("is",ResultNameUtil.getISId());



//        ResultCache resultCache = ResultCache.getInstance();
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
