package com.fzc.fzcsearches.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcsearches.mapper.StockUsImportMapper;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.service.RedisService;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.service.StockUsOtherService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2021/11/23 15:32
 */
@Service
@Slf4j
public class StockUsOtherServiceImpl extends ServiceImpl<StockUsImportMapper, StockUsImport> implements StockUsOtherService {


    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.sector}")
    private String REDIS_KEY_SECTOR;
    @Value("${redis.key.industry}")
    private String REDIS_KEY_INDUSTRY;
    @Value("${redis.key.marketValue}")
    private String REDIS_KEY_MARKET_VALUE;
    @Value("${redis.key.removeNameCom}")
    private String REDIS_KEY_REMOVE_NAME;
    @Value("${redis.key.removeDescCom}")
    private String REDIS_KEY_REMOVE_DESC;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private RedisService redisService;

    @Override
    public List<String> remNameComStr() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_REMOVE_NAME;
        Map<Object, Object> delMap = redisService.hGetAll(key);
        delMap.forEach((k,v)->{
            redisService.hDel(key,k);
        });
        List<StockUsImport> list = stockUsImportService.list();
        Multiset<String> set = HashMultiset.create();
        list.forEach(k -> {
            List<String> strList = SearchUtil.splitWords3(k.getName().toLowerCase());
            strList.forEach(str -> set.add(str));
        });
        Map<String,Integer> map = Maps.newHashMap();
        List<String> res = Lists.newArrayList();
        int num = 0;
        for(String s: Multisets.copyHighestCountFirst(set).elementSet()){
            if (num > 20){
                break;
            }
            num++;
            map.put(s,set.count(s));
            res.add(s);
//            System.out.println(s + ":" + set.count(s));
        }
        redisService.hSetAll(key,map);
        return res;
    }

    @Override
    public List<String> remDescComStr() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_REMOVE_DESC;
        Map<Object, Object> delMap = redisService.hGetAll(key);
        delMap.forEach((k,v)->{
            redisService.hDel(key,k);
        });
        List<StockUsImport> list = stockUsImportService.list();
        Multiset<String> set = HashMultiset.create();
        list.forEach(k -> {
            List<String> strList = SearchUtil.splitWords3(k.getDescription().toLowerCase());
            strList.forEach(str -> set.add(str));
        });
        Map<String,Integer> map = Maps.newHashMap();
        List<String> res = Lists.newArrayList();
        int num = 0;
        for(String s: Multisets.copyHighestCountFirst(set).elementSet()){
            if (num > 30){
                break;
            }
            num++;
            map.put(s,set.count(s));
            res.add(s);
//            System.out.println(s + ":" + set.count(s));
        }
        redisService.hSetAll(key,map);
        return res;
    }

    @Override
    public String convertStr(String str) {
        str = str.toLowerCase();
        String key1 = REDIS_DATABASE + ":" + REDIS_KEY_REMOVE_NAME;
        String key2 = REDIS_DATABASE + ":" + REDIS_KEY_REMOVE_DESC;
        Map<Object, Object> map1 = redisService.hGetAll(key1);
        Map<Object, Object> map2 = redisService.hGetAll(key2);
        Multiset<String> set = HashMultiset.create();
        map1.entrySet().forEach(e -> set.add(e.getKey().toString()));
        map2.entrySet().forEach(e -> set.add(e.getKey().toString()));
        List<String> strList = new ArrayList<String>(SearchUtil.splitWords3(str));
//        run到下面这行s就没了?

//        method one
        List<String> resList =  strList.stream().filter(key -> !set.contains(key)).collect(Collectors.toList());

//        method two
//        strList.removeIf(set::contains);

//        method three
//        strList.forEach(k->{
//           if(set.contains(k)) {
//               boolean res  = strList.remove(k);
//               System.out.println("word:" + k + ":" + res);
//           }
//        });

        String word = Joiner.on(" ").skipNulls().join(strList);
        return word;
    }


}
