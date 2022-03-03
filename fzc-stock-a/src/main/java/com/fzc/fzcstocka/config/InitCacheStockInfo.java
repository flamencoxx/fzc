package com.fzc.fzcstocka.config;

import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2022/3/3 10:38
 */
@Component
public class InitCacheStockInfo implements ApplicationRunner {

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private RedisService redisService;

    public static final String STOCK_AINFO = "stockAInfo";
    @Value("${redis.database}")
    private String REDIS_DATABASE;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = REDIS_DATABASE + ":" + STOCK_AINFO;
        List<StockAInfo> list = stockAInfoService.list();
        Map<String,StockAInfo> map = Maps.newHashMap();
        list.forEach(k->{
           map.put(k.getStockIdentity(),k);
        });
        redisService.del(key);
        redisService.hSetAll(key, map);
    }
}
