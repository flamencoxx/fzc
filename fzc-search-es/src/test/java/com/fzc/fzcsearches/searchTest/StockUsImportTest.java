package com.fzc.fzcsearches.searchTest;

import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.mapper.StockUsImportMapper;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.repository.EsStockUsImportRepository;
import com.fzc.fzcsearches.service.RedisService;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/16 9:20
 */
@SpringBootTest
public class StockUsImportTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;

    @Autowired
    private EsStockUsImportRepository rsStockUsImportRepository;

    @Test
    public void test(){
        List<StockUsImport> list = Lists.newArrayList();
        List<EsStockUsImport> esList = Lists.newArrayList();
        for(int i = 1; i < 10 ; i++){
            StockUsImport stockInfo = stockUsImportMapper.selectById(i);
            list.add(stockInfo);
        }
        list.forEach((k)->{
//            System.out.println(k.toString());
            EsStockUsImport stock = new EsStockUsImport();
            BeanUtils.copyProperties(k,stock);
            esList.add(stock);
        });

        esList.forEach((k)->{
           System.out.println(k.toString());
        });

    }

    @Test
    public void testing2(){
        List<StockUsImport> stockList = Lists.newArrayList();
        stockList = stockUsImportService.list();
        stockList.forEach((k)->{
//            System.out.println(k.toString());
        });
        System.out.println(stockList.size());
    }


}
