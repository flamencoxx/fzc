package com.fzc.fzcsearches.searchTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.mapper.StockUsImportMapper;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.repository.EsStockUsImportRepository;
import com.fzc.fzcsearches.service.RedisService;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author flamenco.xxx
 * @date 2021/11/16 10:39
 */
@SpringBootTest
public class ServiceTest {


    @Autowired
    private RedisService redisService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;

    @Autowired
    private EsStockUsImportRepository rsStockUsImportRepository;


    @Test
    public void test() {
        int result = stockUsImportService.importAll();
        System.out.println(result);
    }

    @Test
    public void Search() {
        String key = "based in Philadelphia";
        List<EsStockUsImport> list = stockUsImportService.searchByKeyword(key);
        list.forEach(k -> {
            System.out.println(k);
        });
    }

    @Test
    public void searchService() {
        String key = "";

    }

    @Test
    public void sector() {
        List<StockUsImport> list = Lists.newArrayList();
        list.addAll(stockUsImportService.list());
        Multiset<String> set = HashMultiset.create();
        list.forEach(s -> {
            List<String> sectorList = SearchUtil.splitWords(s.getSector());
            sectorList.forEach(k -> {
                set.add(k);
            });
        });
        set.elementSet().forEach(s -> {
            System.out.println(s + ":" + set.count(s));
        });
    }

    @Test
    public void testWord() {
        String str = "INFO Creating filter chain";
        String str1 = "org.spring.framework.security.web";
        String str2 = "Ibere Pharmaceuticals focuses on effecting a merger, share exchange,";
        int i = SearchUtil.wordSum(str2);
        System.out.println(i);
    }

    @Test
    public void test3Redis() {
        int i = stockUsImportService.importSectorToRedis();
        System.out.println(i);
    }

    @Test
    public void isSector() {
        String str = "real";
        boolean isSector = stockUsImportService.checkIsSector(str);
        System.out.println(isSector);
    }

    @Test
    public void isIndustry() {
        String str = "";
        int i = stockUsImportService.importIndustryToRedis();
        System.out.println(i);
    }

    @Test
    public void importMarket() {
        int i = stockUsImportService.importMarketValue();
        System.out.println(i);
    }

    @Test
    public void testForSorted() {
        String keyword = "financial";
        List<String> list = Lists.newArrayList();
        QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
        wrapper.like("sector", keyword)
                .orderByDesc("market_value", "name");
        List<StockUsImport> stockList = stockUsImportService.list(wrapper);
        stockList.forEach(k -> list.add(k.getSymbol()));
        list.forEach(k -> System.out.println(k));
    }


    @Test
    public void testSorted() {
        String keyword = "financial";
        List<String> list = Lists.newArrayList();
        QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
        wrapper.like("sector", keyword);
        List<StockUsImport> stockList = stockUsImportService.list(wrapper);
        stockList.forEach(k -> list.add(k.getSymbol()));
        List<String> res = stockUsImportService.sortByValues(list);
        res.forEach(k -> System.out.println(k));
    }

    @Test
    public void Search2() {
        String key = "headquartered in Los Angeles";
        List<EsStockUsImport> list = stockUsImportService.searchByKeyword(key);
        list.forEach(k -> {
            System.out.println(k);
        });
    }

    @Test
    public void removeStr() {
        List<StockUsImport> list = stockUsImportService.list();
        System.out.println(list.size());

    }

    @Test
    public void checkCommonStr() {
        List<StockUsImport> list = stockUsImportService.list();
        System.out.println(list.size());
        Multiset<String> set = HashMultiset.create();
        list.forEach(k -> {
            List<String> strList = SearchUtil.splitWords(k.getName());
            strList.forEach(str -> set.add(str));
        });
        set.elementSet().forEach(k -> {
//            System.out.println(k + ":" + set.count(k));
        });
        List<Set<String>> sortList = new ArrayList<>(Collections.singleton(set.elementSet()));
        for (String s : Multisets.copyHighestCountFirst(set).elementSet()) {
            System.out.println(s + ":" + set.count(s));
        }
    }

    @Test
    public void removeDescCom(){
        List<StockUsImport> list = stockUsImportService.list();
        Multiset<String> set = HashMultiset.create();
        list.forEach(k -> {
            List<String> strList = SearchUtil.splitWords(k.getDescription());
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
            System.out.println(s + ":" + set.count(s));
        }
    }

}







