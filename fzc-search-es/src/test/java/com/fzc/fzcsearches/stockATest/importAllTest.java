package com.fzc.fzcsearches.stockATest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Console;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.model.StockAInfo;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockInfoService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
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
    private StockInfoService service;


    @Test
    public void test1() {
        esDao.deleteAll();
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
        while (it.hasNext()) {
            res++;
            it.next();
        }
        Console.log(res);
    }

    @Test
    public void test2() {
        List<StockAInfo> stockList = service.list();
        stockList.forEach(k -> {
            String detail = getDetail(k.getTsCode());
            k.setDetail(detail);
            service.updateById(k);
        });
    }

    @Test
    public void test3() {
        String code = "002356.SZ";
        String res = getDetail(code);
        Console.log(res);
    }

    @Test
    public void test4() {
        esDao.findAll().forEach(k -> {
            String detail = k.getDetail();
            Console.log(detail);
        });
    }

    @Test
    public void test5() {
        String key = "0000";
        List<EsStockAimport> list = service.searchByKeyword(key);
        list.forEach(k -> {
            Console.log(k.toString());
        });
    }

    public String getDetail(String code) {
        List<String> codes = Splitter.on(".").splitToList(code);
        return Joiner.on("").join(codes.get(1).toLowerCase(), codes.get(0));
    }

}
