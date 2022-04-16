package com.fzc.fzcfutu.api;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ObjectUtil;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Flamenco.xxx
 * @date 2022/4/12 9:22
 */
@RestController
@RequestMapping("/InfoTransform")
public class InfoTransformController {

    @Autowired
    private StockInfoRepository stockInfoRepository;

    @RequestMapping("/stockInfo")
    public String stockInfo(){
        return "stockInfo";
    }

    @RequestMapping("/findStockInfo")
    public StockInfoDO getStockInfo(@RequestParam("stockCode") String stockCode){
        TimeInterval interval = new TimeInterval();
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol(stockCode);
        if(ObjectUtil.isEmpty(stockInfoDO)){
            return null;
        }
        System.out.println("findtime = " + interval.interval());
        stockInfoDO.setStockHistoricalDataList(null);
        return stockInfoDO;
    }
}
