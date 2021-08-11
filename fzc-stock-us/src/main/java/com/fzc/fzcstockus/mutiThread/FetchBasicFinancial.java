package com.fzc.fzcstockus.mutiThread;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Flamenco.xxx
 * @date 2021/8/11 13:21
 */
@Component
@Slf4j
public class FetchBasicFinancial implements Runnable{



    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    private String code;

    public FetchBasicFinancial(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FetchBasicFinancial(){

    }

    @Override
    public void run() {
        BasicFinancials basicFinancials = stockBasicFinancialService.findBasicFinancialsBySymbol(code);

//        System.out.println("成功刷新BasicFinancial数据");

        log.info("成功刷新BasicFinancial数据");



    }
}
