package com.fzc.fzcfutu.ServiceTesting;

import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockHistoryService;
import com.fzc.fzcfutu.service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 9:26
 */
@SpringBootTest
public class fetchHIstortTesting {

    @Autowired
    private StockHistoryService stockHistoryService;


    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;


    @Test
    public void testingFetchHistory(){
        System.out.println(
                new SimpleDateFormat("yyyyMMdd").format(new Date())
        );
        String code = "000001";
        boolean bool = stockHistoryService.fetchHistoryData(code);
        System.out.println(bool);
    }


    @Test
    public void test1(){
        System.out.println(
                new SimpleDateFormat("yyyyMMdd").format(new Date())
        );
    }
}
