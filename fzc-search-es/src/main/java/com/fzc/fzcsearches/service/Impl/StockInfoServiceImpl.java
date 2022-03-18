package com.fzc.fzcsearches.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.mapper.StockAInfoMapper;
import com.fzc.fzcsearches.model.StockAInfo;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:34
 */
@Service
@Slf4j
public class StockInfoServiceImpl extends ServiceImpl<StockAInfoMapper, StockAInfo> implements StockInfoService {

    @Autowired
    private EsStockAImportRepository esDao;

    @Autowired
    private StockInfoService service;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public int importAll() {
//        if (esDao.existsById(1L)){
//            esDao.deleteAll();
//        }
        List<StockAInfo> stockList = service.list();
        List<EsStockAimport> esList = Lists.newLinkedList();
        stockList.forEach(st -> {
            EsStockAimport es = new EsStockAimport();
            BeanUtil.copyProperties(st, es);
            esList.add(es);
        });
        Iterable<EsStockAimport> esIt = esDao.saveAll(esList);
        Iterator<EsStockAimport> it = esIt.iterator();
        int res = 0;
        while(it.hasNext()){
            res++;
            it.next();
        }
        return res;
    }

    @Override
    public List<EsStockAimport> searchByKeyword(String keyword) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if(StrUtil.isBlank(keyword)){
            return new ArrayList<>();
        }else{
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.prefixQuery("name", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(7)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("stockIdentity", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("code", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("code", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("enname", keyword).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
//            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
//                    ScoreFunctionBuilders.weightFactorFunction(3)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("name", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("fullname", keyword).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(3)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("fullname", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(7)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.prefixQuery("symbol", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.fuzzyQuery("tsCode", keyword).fuzziness(Fuzziness.AUTO),
                    ScoreFunctionBuilders.weightFactorFunction(5)));

            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<EsStockAimport> searchHits = elasticsearchRestTemplate.search(searchQuery, EsStockAimport.class);
        if(searchHits.getTotalHits()<=0){
            return new ArrayList<>();
        }
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
