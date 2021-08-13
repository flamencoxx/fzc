package com.fzc.fzcstockus.controller;

import cn.hutool.json.JSONObject;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsHistoryTableService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Flamenco.xxx
 * @date 2021/8/12 12:55
 */
@CrossOrigin()
@Controller
@RequestMapping(value ="/UsHistoryTable")
@Slf4j
public class StockUsHistoryTableController {

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

    @Autowired
    private StockUsHistoryTableService stockUsHistoryTableService;



    @GetMapping(value="data")
    public ResponseEntity<JSONObject> UsHistoryTable(@RequestParam(value="code")String code){
        JSONObject jsonObject = null;
        if(stockUsInfoDoRepository.existsBySymbol(code.toUpperCase())){
            jsonObject = stockUsHistoryTableService.findUsTable(code.toLowerCase());
        }else{
            log.info("查询HistoryTable不存在的股票");
            return ResponseEntity.ok(null);
        }
//        JSONObject jsonObject = stockUsHistoryTableService.findUsTable(code.toLowerCase());

        return ResponseEntity.ok(jsonObject);
    }


}
