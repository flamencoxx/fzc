package com.fzc.fzcstocka.config;

import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
