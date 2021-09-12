package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.AsyncPeerService;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Flamenco.xxx
 * @date 2021/7/29 9:38
 */
@Service
public class StockPeerServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockPeerService {


    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;

    @Autowired
    private AsyncPeerService asyncPeerService;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;


    @Override
    public List<StockUsInfoDo> findPeerListBySymbol(String symbol) {
        List<StockUsInfoDo> resultList = new ArrayList<StockUsInfoDo>();

        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        List<String> peerList = stock.getPeerList();

        for(String s:peerList){
            if(stockUsInfoDoRepository.existsBySymbol(s)){
                StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(s);
                resultList.add(stockUsInfoDo);
            }
        }

        return resultList;
    }

    @Override
    public List<StockUsInfoDo> findPeerListBySymbolAsync(String symbol) {

        //        创建线程池

//        ThreadPoolExecutor threadPool =
//                new ThreadPoolExecutor(6,10,60,
//                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));

        List<StockUsInfoDo> resultList = new ArrayList<StockUsInfoDo>();

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
                StockUsInfoDo s = future.get();
                resultList.add(s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

//        for(String s:peerList){
//            if(stockUsInfoDoRepository.existsBySymbol(s)){
//                StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(s);
//                resultList.add(stockUsInfoDo);
//            }
//        }

        return resultList;
    }
}
