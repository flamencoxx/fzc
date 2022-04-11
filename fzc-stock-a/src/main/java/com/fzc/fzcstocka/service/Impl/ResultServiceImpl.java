package com.fzc.fzcstocka.service.Impl;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.model.ScoreInfo;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.service.ResultService;
import com.fzc.fzcstocka.util.ResultCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2022/2/24 9:47
 */
@Service
@Slf4j
public class ResultServiceImpl implements ResultService {

    public static final String NONE = "None";
    @Autowired
    private ResultAnalyzerRepository resultAnalyzerRepository;


    private final ImmutableSet<String> resultSet = ImmutableSet.of("cf", "mf", "pr", "ac", "is","ls","ta");

    Map<String, String> resultMap = Maps.newHashMap();
    private final ImmutableMap<String, String> scoreInfoMap = new ImmutableMap.Builder<String, String>()
            .put("cf", "cfResultAnalyzer")
            .put("mf", "mfResultAnalyzer")
            .put("pr", "prResultAnalyzer")
            .put("ac", "acResultAnalyzer")
            .put("is", "isResultAnalyzer")
            .put("ls", "lsResultAnalyzer")
            .put("ta", "taResultAnalyzer")
            .build();

    @PostConstruct
    public void init() {
        resultMap.put("cf", "cfResultAnalyzer");
        resultMap.put("mf", "mfResultAnalyzer");
        resultMap.put("pr", "prResultAnalyzer");
        resultMap.put("ac", "acResultAnalyzer");
        resultMap.put("is", "isResultAnalyzer");
        resultMap.put("ls", "lsResultAnalyzer");
        resultMap.put("ta", "taResultAnalyzer");
    }

    @Override
    public List<ResultAnalyzer> getCfList(String code) {
        if (StringUtils.isNotBlank(code)) {
            return new ArrayList<>();
        }
        ResultCache cache = ResultCache.getInstance();
        String str = cache.cache.getIfPresent("cf");
        List<ResultAnalyzer> resultAnalyzers = resultAnalyzerRepository.findByStockIdentityAndAnalyzer(code, str);
        if (resultAnalyzers.size() == 0) {
            return new ArrayList<>();
        }
        resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
        return resultAnalyzers;
    }

    @Override
    public List<ResultAnalyzer> getMfList(String code) {
        if (StringUtils.isNotBlank(code)) {
            return new ArrayList<>();
        }
        ResultCache cache = ResultCache.getInstance();
        String str = cache.cache.getIfPresent("mf");
        List<ResultAnalyzer> resultAnalyzers = resultAnalyzerRepository.findByStockIdentityAndAnalyzer(code, str);
        if (resultAnalyzers.size() == 0) {
            return new ArrayList<>();
        }
        resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
        return resultAnalyzers;
    }

    @Override
    public List<ResultAnalyzer> getPrList(String code) {
        if (StringUtils.isNotBlank(code)) {
            return new ArrayList<>();
        }
        ResultCache cache = ResultCache.getInstance();
        String str = cache.cache.getIfPresent("pr");
        List<ResultAnalyzer> resultAnalyzers = resultAnalyzerRepository.findByStockIdentityAndAnalyzer(code, str);
        if (resultAnalyzers.size() == 0) {
            return new ArrayList<>();
        }
        resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
        return resultAnalyzers;
    }

    @Override
    public List<ResultAnalyzer> getAcList(String code) {
        if (StringUtils.isBlank(code)) {
            return new ArrayList<>();
        }
        ResultCache cache = ResultCache.getInstance();
        String str = cache.cache.getIfPresent("ac");
        List<ResultAnalyzer> resultAnalyzers = resultAnalyzerRepository.findByStockIdentityAndAnalyzer(code, str);
        if (resultAnalyzers.size() == 0) {
            return new ArrayList<>();
        }
        resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
        return resultAnalyzers;
    }

    @Override
    public List<ResultAnalyzer> getIsList(String code) {
        if (StringUtils.isNotBlank(code)) {
            return new ArrayList<>();
        }
        ResultCache cache = ResultCache.getInstance();
        String str = cache.cache.getIfPresent("is");
        List<ResultAnalyzer> resultAnalyzers = resultAnalyzerRepository.findByStockIdentityAndAnalyzer(code, str);
        if (resultAnalyzers.size() == 0) {
            return new ArrayList<>();
        }
        resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
        return resultAnalyzers;
    }

    @Override
    public List<ResultAnalyzer> getAllList(String code) {
        if (StringUtils.isNotBlank(code)) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    @Override
    public ScoreInfo getComprehensiveCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return new ScoreInfo();
        }
        TimeInterval interval = new TimeInterval();
        List<ResultAnalyzer> list = resultAnalyzerRepository.findByStockIdentity(code);
        Console.log(interval.intervalMs() + "ms--test");
        if (list == null || list.size() == 0) {
            return new ScoreInfo();
        }
        ResultCache cache = ResultCache.getInstance();
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger passCount = new AtomicInteger(0);
        AtomicInteger result = new AtomicInteger();
        AtomicInteger passResult = new AtomicInteger();
        ScoreInfo scoreInfo = new ScoreInfo();
        resultSet.forEach(k -> {
            String fieldName = scoreInfoMap.get(k);
            List<ResultAnalyzer> resultAnalyzers = list.stream().filter(o -> o.getAnalyzer().equals(cache.cache.getIfPresent(k))).collect(Collectors.toList());
            if (resultAnalyzers.size() > 0) {
                resultAnalyzers = resultAnalyzers.stream().sorted((o1, o2) -> o2.getPeriod().compareTo(o1.getPeriod())).collect(Collectors.toList());
                ResultAnalyzer resultAnalyzer = resultAnalyzers.get(0);
                if (NONE.equals(resultAnalyzer.getScore())) {
                    return;
                }
                ResultAnalyzer passResultAnalyzer = null;
                if (resultAnalyzers.get(1) != null) {
                    passResultAnalyzer = resultAnalyzers.get(1);
                }
                Field field = null;
                try {
                    field = scoreInfo.getClass().getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                assert field != null;
                field.setAccessible(true);
                try {
                    field.set(scoreInfo,resultAnalyzer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    field.setAccessible(false);
                }
                Console.log(resultAnalyzer.getAnalyzer() + ":" + resultAnalyzer.getPeriod());
                if (NONE.equals(resultAnalyzer.getScore())) {
                    return;
                }
                result.set(result.get() + Integer.parseInt(resultAnalyzer.getScore()));
                if(passResultAnalyzer != null){
                    passResult.set(passResult.get() + Integer.parseInt(passResultAnalyzer.getScore()));
                    passCount.set(passCount.get() + 1);
                }
                count.set(count.get() + 1);
            }
        });
        scoreInfo.setComprehensiveScore(String.valueOf(result.get() / count.get()));
        scoreInfo.setPassComprehensiveScore(String.valueOf(passResult.get() / passCount.get()));
        return scoreInfo;
    }

    @Override
    @Async
    public Future<ScoreInfo> AsyncGetComprehensiveCode(String code) {
        ScoreInfo res = this.getComprehensiveCode(code);
        return new AsyncResult<>(res);
    }
}
