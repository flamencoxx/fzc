package com.fzc.fzcsearches.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.mapper.StockUsImportMapper;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.repository.EsStockUsImportRepository;
import com.fzc.fzcsearches.service.RedisService;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.service.StockUsOtherService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
@Slf4j
public class StockUsImportServiceImpl  extends ServiceImpl<StockUsImportMapper, StockUsImport> implements StockUsImportService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;

    @Autowired
    private EsStockUsImportRepository esStockUsImportRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private StockUsOtherService stockUsOtherService;

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


    @Override
    public int importSectorToRedis() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SECTOR;
        Set<Object> setMembers = redisService.sMembers(key);
        setMembers.forEach(s ->{
           long i = redisService.sRemove(key,s);
        });
        List<StockUsImport> list = Lists.newArrayList();
        list.addAll(stockUsImportService.list());
        Multiset<String> set = HashMultiset.create();
        list.forEach(s -> {
            List<String> sectorList = SearchUtil.splitWords3(s.getSector());
            sectorList.forEach(k ->{
                set.add(k);
            });
        });
        set.elementSet().forEach(s -> {
            long num = redisService.sAdd(key,s);
        });
        Long de = redisService.sRemove(key,"");
        return set.elementSet().size();
    }

    @Override
    public int importIndustryToRedis() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_INDUSTRY;
        Set<Object> setMembers = redisService.sMembers(key);
        setMembers.forEach(s ->{
            long i = redisService.sRemove(key,s);
        });
        List<StockUsImport> list = Lists.newArrayList();
        list.addAll(stockUsImportService.list());
        Multiset<String> set = HashMultiset.create();
        list.forEach(s -> {
            List<String> sectorList = SearchUtil.splitWords3(s.getIndustry());
            sectorList.forEach(k ->{
                set.add(k);
            });
        });
        set.elementSet().forEach(s -> {
            long num = redisService.sAdd(key,s.toLowerCase());
        });
        Long de = redisService.sRemove(key,"");
        return set.elementSet().size();
    }

    @Override
    public int importAll() {
        int result = 0;
        try {
            List<StockUsImport> stockList = Lists.newArrayList();
            List<EsStockUsImport> esList = Lists.newArrayList();
            stockList = stockUsImportService.list();
            stockList.forEach(k->{
                EsStockUsImport stock = new EsStockUsImport();
                BeanUtils.copyProperties(k,stock);
                esList.add(stock);
            });
            Iterable<EsStockUsImport> esIt = esStockUsImportRepository.saveAll(esList);
            Iterator<EsStockUsImport> it = esIt.iterator();
            while (it.hasNext()) {
                result++;
                it.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int importMarketValue() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MARKET_VALUE;
        Map<Object, Object> delMap = redisService.hGetAll(key);
        delMap.forEach((k,v) ->{
            redisService.hDel(key,k);
        });
        List<StockUsImport> list = Lists.newArrayList();
        list.addAll(stockUsImportService.list());
        Multiset<String> set = HashMultiset.create();
        HashMap<String,String> map = Maps.newHashMap();
        list.forEach(s -> {
            map.put(s.getSymbol(),s.getMarketValue());
            });
        redisService.hSetAll(key,map);

        return map.size();
    }

    @Override
    public List<EsStockUsImport> searchByKeyword(String keyword) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isEmpty(keyword)) {
            return new ArrayList<EsStockUsImport>();
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.prefixQuery("symbol", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("name", keyword).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("description", keyword).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }

        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<EsStockUsImport> searchHits = elasticsearchRestTemplate.search(searchQuery, EsStockUsImport.class);
        if(searchHits.getTotalHits()<=0){
            return new ArrayList<>();
        }
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public List<EsStockUsImport> searchByKeywords(String keywords) {
        keywords = stockUsOtherService.convertStr(keywords);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isEmpty(keywords)) {
            return new ArrayList<EsStockUsImport>();
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("name", keywords).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("description", keywords).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }

        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<EsStockUsImport> searchHits = elasticsearchRestTemplate.search(searchQuery, EsStockUsImport.class);
        if(searchHits.getTotalHits()<=0){
            return new ArrayList<>();
        }
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());

    }

    @Override
    public boolean checkIsSector(String word) {
        word = word.trim();
        word = word.toLowerCase();
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SECTOR;
        boolean result = redisService.sIsMember(key,word);
        return result;
    }

    @Override
    public boolean checkIsIndustry(String word) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_INDUSTRY;
        boolean result = redisService.sIsMember(key,word);
        return result;
    }

    @Override
    public List<String> sortByValues(List<String> list) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MARKET_VALUE;
        List<String> res = Lists.newArrayList();
        Map<String, Long> map = Maps.newTreeMap();
        list.forEach(e -> {
            long value = Long.parseLong(String.valueOf(redisService.hGet(key,e)));
            map.put(e, value);
        });
        System.out.println("treemap:");
        map.forEach((k,v)->{
           System.out.println(k + ":" + v);
        });
        List<Map.Entry<String,Long>> sortList = new ArrayList<Map.Entry<String,Long>>(map.entrySet());
        sortList.sort(Map.Entry.comparingByValue());

        sortList.forEach(e -> {
            res.add(e.getKey());
            System.out.println(e.getKey() + ":" + e.getValue());
        });
        Collections.reverse(res);
        return res;
    }
}