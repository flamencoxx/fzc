package com.fzc.fzcstockus.service;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.repository.StockUsDORepository;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.AsyncPeerService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Autowired
    private AsyncPeerService asyncPeerService;

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

    @Test
    public void error(){
        String symbol = "IBM";
        List<StockUsInfoDo> resultList = new ArrayList<StockUsInfoDo>();

        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<String> peerList = stock.getPeerList();

        for(String s:peerList){
            if(stockUsInfoDoRepository.existsBySymbol(s)){
                StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(s);
                resultList.add(stockUsInfoDo);
            }
        }
        System.out.println(resultList);
    }

    @Test
    public void asyncPeer(){
        List<StockUsInfoDo> resultList = new ArrayList<StockUsInfoDo>();

        String symbol = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<String> peerList = stock.getPeerList();
        List<Future<StockUsInfoDo>> futureList = new ArrayList<>();

        for(String s:peerList){
            if(stockUsInfoDoRepository.existsBySymbol(s)){
                Future<StockUsInfoDo> future = asyncPeerService.asyncPeer(s);
                futureList.add(future);
            }
        }

        for(Future<StockUsInfoDo> future:futureList){
            try {
                StockUsInfoDo s = (StockUsInfoDo)future.get();
                resultList.add(s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(resultList);
    }


}
