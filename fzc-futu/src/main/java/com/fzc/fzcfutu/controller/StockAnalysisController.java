package com.fzc.fzcfutu.controller;

import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockFinancialService;
import com.fzc.fzcfutu.service.StockHistoryService;
import com.fzc.fzcfutu.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yahoofinance.Stock;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 18:12
 */

@Controller
public class StockAnalysisController {

    @Autowired
    private StockFinancialService stockFinancialService;

    @Autowired
    private StockHistoryService stockHistoryService;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockInfoRepository stockInfoRepository;




}
