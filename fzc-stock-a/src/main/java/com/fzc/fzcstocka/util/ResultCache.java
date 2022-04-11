package com.fzc.fzcstocka.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fzc.fzcstocka.model.ScoreInfo;
import com.fzc.fzcstocka.service.ResultService;
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

    private final static ResultCache INSTANCE = new ResultCache();

    private static ResultService resultService;


    private ResultCache() {

    }

    @PostConstruct
    public void init() {
        resultService = resultService1;
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
}
