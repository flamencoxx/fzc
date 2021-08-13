package com.fzc.fzcstockus.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/8/9 17:31
 */
@CrossOrigin()
@Controller
@RequestMapping(value ="/OtherDetails")
public class StockUsOtherDetailsController {
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


    @GetMapping(value="data")
    public ResponseEntity<JSONObject> findOtherDetailsOfUsStock(@RequestParam(value="code")String code){
        JSONObject jsonObject = JSONUtil.createObj();

        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code.toUpperCase());
        String description = stockUsInfoDo.getCompanyOverview().getDescription();
        String marketValue =stockUsInfoDo.getCompanyOverview().getMarketCapitalization();
        String peRatio = stockUsInfoDo.getCompanyOverview().getPERatio();
        String pegRatio = stockUsInfoDo.getCompanyOverview().getPEGRatio();
        String revenue = stockUsInfoDo.getCompanyOverview().getRevenueTTM();
        String grossProfit = stockUsInfoDo.getCompanyOverview().getGrossProfitTTM();
        String sector = stockUsInfoDo.getCompanyOverview().getSector();


//        BasicFinancials basicFinancials = new BasicFinancials();
//        if(!"".equals(code)){
//            basicFinancials = stockBasicFinancialService.findBasicFinancialsBySymbol(code.toUpperCase());
//            stockUsInfoDo = stockCompanyInfoService.findStockCompanyInfo(code.toUpperCase());
//        }



        jsonObject.put("description",description);
        jsonObject.put("PERatio",peRatio);
        jsonObject.put("MarketCapitalization",marketValue);
        jsonObject.put("PEGRatio",pegRatio);
        jsonObject.put("RevenueTTM",revenue);
        jsonObject.put("GrossProfitTTM",grossProfit);
        jsonObject.put("Sector",sector);



//        创建线程池
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(6,10,60,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));


        fetchBasicFinancial.setCode(code.toUpperCase());
        fetchCompanyInfo.setCode(code.toUpperCase());
        threadPool.execute(fetchBasicFinancial);

        threadPool.execute(fetchCompanyInfo);

        threadPool.shutdown();


        return ResponseEntity.ok(jsonObject);
    }

}
