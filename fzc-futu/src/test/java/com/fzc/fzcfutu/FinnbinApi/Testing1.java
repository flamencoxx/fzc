package com.fzc.fzcfutu.FinnbinApi;

import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockInfoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class Testing1 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Test
    public void findAllUsList(){
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol("002603");
    }

}
