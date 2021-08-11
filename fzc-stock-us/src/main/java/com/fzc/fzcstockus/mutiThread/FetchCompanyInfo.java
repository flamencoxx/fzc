package com.fzc.fzcstockus.mutiThread;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Flamenco.xxx
 * @date 2021/8/11 18:43
 */
@Component
@Slf4j
public class FetchCompanyInfo implements Runnable{

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FetchCompanyInfo(){}

    public FetchCompanyInfo(String code){
        this.code =code;
    }
    @Override
    public void run() {
        StockUsInfoDo stockUsInfoDo = stockCompanyInfoService.findStockCompanyInfo(code.toUpperCase());
//        System.out.println("成功刷新companyInfo");
        log.info("成功刷新companyInfo");
    }
}
