package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
