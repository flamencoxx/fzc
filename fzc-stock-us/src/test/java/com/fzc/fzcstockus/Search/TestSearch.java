package com.fzc.fzcstockus.Search;

import com.fzc.fzcstockus.mapper.StockUsImportMapper;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.producer.BasicFinancialProducer;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.*;
import com.fzc.fzcstockus.util.SearchUtil;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/8 15:34
 */
@SpringBootTest
public class TestSearch {
    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private FetchBasicFinancial fetchBasicFinancial;

    @Autowired
    private FetchCompanyInfo fetchCompanyInfo;

    @Autowired
    private StockUsHistoryTableService stockUsHistoryTableService;

    @Autowired
    private BasicFinancialProducer basicFinancialProducer;


    @Autowired
    private StockUsImportService service;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;


    @Autowired
    private StockUsImportService stockUsImportService;



    @Test
    public void test1(){
        String symbol = "IBM";
        String words = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol).getCompanyOverview().getDescription();
        List<String> list = SearchUtil.splitWords(words);
        list.forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<StockUsImport> list = Lists.newArrayList();
        list.addAll(stockUsImportService.list());
        Multiset<String> set = HashMultiset.create();
        list.forEach(s -> {
            set.add(s.getSector());
        });
        set.elementSet().forEach(s -> {
            System.out.println(s + ":" + set.count(s));
        });
    }

}
