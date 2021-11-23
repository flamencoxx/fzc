package com.fzc.fzcsearches.searchTest;

import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.mapper.StockUsImportMapper;
import com.fzc.fzcsearches.repository.EsStockUsImportRepository;
import com.fzc.fzcsearches.service.RedisService;
import com.fzc.fzcsearches.service.StockUsImportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/16 14:45
 */
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private RedisService redisService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;

    @Autowired
    private EsStockUsImportRepository esStockUsImportRepository;

    @Test
    public void test(){
        String key = "Applied DNA Sciences";
        List<EsStockUsImport> list = esStockUsImportRepository.findEsStockUsImportsByDescriptionOrNameIsLikeOrSymbolIsLike(key,key,key);
        list.forEach(k ->{
            System.out.println(k);
        });
    }

    @Test
    public void test1(){
        String key = "IBM";
        List<EsStockUsImport> list = esStockUsImportRepository.findEsStockUsImportsBySymbol(key);
                list.forEach(k ->{
                   System.out.println(k);
                });
    }

    @Test
    public void test2(){
        String key = "app";
        List<EsStockUsImport> list = esStockUsImportRepository.findEsStockUsImportsByDescriptionOrNameOrSymbol(key,key,key);
        list.forEach(k ->{
            System.out.println(k);
        });
    }

}
