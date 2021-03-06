package com.fzc.fzcstockus.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.StockProfile2;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockProfile2Service;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/8/9 17:31
 */
@CrossOrigin()
@Controller
@Slf4j
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

    @Autowired
    private StockProfile2Service stockProfile2Service;

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

        QueryWrapper<StockProfile2> wrapper = new QueryWrapper<>();
        wrapper.eq("symbol",code.toUpperCase());
        StockProfile2 stockProfile2 = stockProfile2Service.getOne(wrapper);
        String webUrl = stockProfile2.getWeburl();
        String logo = stockProfile2.getLogo();


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
        jsonObject.put("webUrl",webUrl);
        jsonObject.put("logo",logo);



//        ???????????????
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(6,10,60,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));


//        fetchBasicFinancial.setCode(code.toUpperCase());
        fetchCompanyInfo.setCode(code.toUpperCase());
//        threadPool.execute(fetchBasicFinancial);

        threadPool.execute(fetchCompanyInfo);

        threadPool.shutdown();

        log.info("????????????table????????????");

        return ResponseEntity.ok(jsonObject);
    }

}
