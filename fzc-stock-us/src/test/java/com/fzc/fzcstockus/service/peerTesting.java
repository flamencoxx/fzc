package com.fzc.fzcstockus.service;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.repository.StockUsDORepository;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockPeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/7/29 9:42
 */
@SpringBootTest
public class peerTesting {

    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;

    @Test
    public void findPeerList(){
        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<String> peerList = stock.getPeerList();
        boolean b = stockUsInfoDoRepository.existsBySymbol(symbol);
        System.out.println(peerList);
        System.out.println(b);
    }

    @Test
    public void peerServiceTest(){
        String str = "IBM";
        List<StockUsInfoDo> list = stockPeerService.findPeerListBySymbol(str);
        for(StockUsInfoDo s: list){
            String symbol = s.getSymbol();
            System.out.println(symbol);
        }
//        System.out.println(list);
    }

    @Test
    public void testing(){
        int i = 2 << 3 ;
        System.out.println(i);
    }


}
