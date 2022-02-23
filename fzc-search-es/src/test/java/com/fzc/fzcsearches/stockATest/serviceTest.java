package com.fzc.fzcsearches.stockATest;

import cn.hutool.core.lang.Console;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockInfoService;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 17:29
 */
@SpringBootTest
public class serviceTest {

    @Autowired
    private StockInfoService service;

    @Autowired
    private EsStockAImportRepository esStockAImportRepository;

    @Autowired
    private StockUsImportService usService;

    @Test
    public void test0(){
        String str = "Ibere";
        List<EsStockUsImport> list = usService.searchByKeyword(str);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }


    @Test
    public void test(){
        String key = "中国";
        List<EsStockAimport> list = esStockAImportRepository.findEsStockAimportByNameIsLike(key);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void test1(){
        String key = "China";
        String key1 = "000002sz";
        String key3 = "平安";
        String key4 = "龙源";
        String key5 = "企业股份";
        List<EsStockAimport> list = service.searchByKeyword(key5);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void test2(){
        String key = "000";
        List<EsStockAimport> list = service.searchByKeyword(key);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void test3(){
        String key = "股份有限公司";
        List<EsStockAimport> list = service.searchByKeyword(key);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void test4(){
        String key = "60";
        List<EsStockAimport> list = service.searchByKeyword(key);
        list.forEach(k->{
            Console.log(k.toString());
        });
    }

    @Test
    public void test5(){
        Iterable<EsStockAimport> list = esStockAImportRepository.findAll();
        Iterator<EsStockAimport> it = list.iterator();
        Multiset<String> set = HashMultiset.create();
        list.forEach(k->{
           set.add(k.getIndustry());
        });
        set.elementSet().remove(null);
        AtomicInteger res = new AtomicInteger();
        set.elementSet().forEach(k->{
            Console.log(k + ":" + set.count(k));
            res.getAndIncrement();
        });

        Console.log(res);
    }

}
