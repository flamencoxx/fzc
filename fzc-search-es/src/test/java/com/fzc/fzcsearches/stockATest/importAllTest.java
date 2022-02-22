package com.fzc.fzcsearches.stockATest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Console;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.model.StockAInfo;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockAInfoService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 15:46
 */
@SpringBootTest
public class importAllTest {

    @Autowired
    private EsStockAImportRepository esDao;

    @Autowired
    private StockAInfoService service;


    @Test
    public void test1(){
        List<StockAInfo> stockList = service.list();
        List<EsStockAimport> esList = Lists.newLinkedList();
        Console.log(stockList.size());
        stockList.forEach(st -> {
            EsStockAimport es = new EsStockAimport();
            BeanUtil.copyProperties(st, es);
            esList.add(es);
        });
        Console.log(esList.size());
        Iterable<EsStockAimport> esIt = esDao.saveAll(esList);
        Iterator<EsStockAimport> it = esIt.iterator();
        int res = 0;
        while(it.hasNext()){
            res++;
            it.next();
        }
        Console.log(res);
    }

}
