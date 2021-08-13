package com.fzc.fzcstockus.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.Annual;
import com.fzc.fzcstockus.model.CashRatio;
import com.fzc.fzcstockus.model.CurrentRatio;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/12 13:02
 */
@SpringBootTest
public class UsHistoryTableTesting {
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

    @Test
    public void testing(){
        String code = "ibm";
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code.toUpperCase());
        Annual annual = stockUsInfoDo.getBasicFinancials().getAnnual();
        System.out.println(annual);
    }

    @Test
    public void test1(){

        JSONObject jsonObject = JSONUtil.createObj();

        JSONArray data = JSONUtil.createArray();
        String code = "IBER";
        List<String> dateList = new ArrayList<String>();
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code.toUpperCase());
        Annual annual = new Annual();
        try {
            annual = stockUsInfoDo.getBasicFinancials().getAnnual();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        List<CashRatio> cashRatioList = annual.getCashRatio();

        for(CashRatio cashRatio: cashRatioList){
            String date = cashRatio.getPeriod();
            dateList.add(date);
        }

        for(String date : dateList){
            JSONObject singleJson = JSONUtil.createObj();
            singleJson.put("date",date);
            for(CashRatio cashRatio : cashRatioList){
                if(cashRatio.getPeriod().equals(date)){
                    singleJson.put("CashRatio",cashRatio.getV());
                }
            }

            List<CurrentRatio> currentRatioList = annual.getCurrentRatio();

            for(CurrentRatio currentRatio : currentRatioList){
                if(currentRatio.getPeriod().equals(date)){
                    singleJson.put("CurrentRatio",currentRatio.getV());
                }
            }


            data.add(singleJson);
        }
        jsonObject.put("data",data);

        System.out.println(dateList);
        System.out.println(jsonObject);
    }

}
