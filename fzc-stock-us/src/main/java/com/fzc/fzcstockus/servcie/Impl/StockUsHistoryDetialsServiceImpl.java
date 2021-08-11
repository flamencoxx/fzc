package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

/**
 * @author Flamenco.xxx
 * @date 2021/8/9 17:35
 */
@Service
public class StockUsHistoryDetialsServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockUsHistoryDetialsService {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;


    @Override
    public String showDescription(String code) {
        return null;
    }
}
