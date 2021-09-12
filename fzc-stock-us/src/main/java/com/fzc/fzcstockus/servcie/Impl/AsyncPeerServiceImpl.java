package com.fzc.fzcstockus.servcie.Impl;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.AsyncPeerService;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Flamenco.xxx
 * @date 2021/9/11 17:50
 */
@Service
@Slf4j
public class AsyncPeerServiceImpl implements AsyncPeerService {

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private FetchBasicFinancial fetchBasicFinancial;

    @Autowired
    private FetchCompanyInfo fetchCompanyInfo;

    @Override
    @Async
    public Future<StockUsInfoDo> asyncPeer(String symbol) {
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        return new AsyncResult<>(stockUsInfoDo);
    }
}
