package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.Eps;
import com.fzc.fzcstockus.model.HistoryPrice;
import com.fzc.fzcstockus.model.PERatio;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsDORepository;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockPeerService;
import com.fzc.fzcstockus.servcie.StockUsHistoryDetialsService;
import com.fzc.fzcstockus.servcie.StockUsHistoryTableService;
import com.fzc.fzcstockus.servcie.StockUsPEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/27 11:15
 */
@Service
@Slf4j
public class StockUsPEServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockUsPEService {

    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;

    @Autowired
    private StockUsHistoryDetialsService stockUsHistoryDetialsService;


    @Override
    public boolean fetchPEList(String symbol) {


        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol.toUpperCase());
        boolean result = false;

        List<Eps> epsList = null;
        try {
            epsList = stock.getBasicFinancials().getAnnual().getEps();
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("刷新Pe列表结果:" + symbol + "----result:" + false);
            return false;
        }
//        List<String> dateList = new ArrayList<String>();
//        for (Eps ep : epsList) {
//            dateList.add(ep.getPeriod());
//        }

        List<HistoryPrice> resultList = new ArrayList<HistoryPrice>();
        List<HistoryPrice> historyPriceList = null;
        try {
            historyPriceList = stock.getHistoryPrices();
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("刷新Pe列表结果:" + symbol + "----result:" + false);
            return false;
        }
        List<PERatio> peRatioList = new ArrayList<PERatio>();


        if (epsList.isEmpty()) {
            log.info("刷新Pe列表结果:" + symbol + "----result:" + false);
            return false;
        }
        if (historyPriceList.isEmpty()) {
            log.info("刷新Pe列表结果:" + symbol + "----result:" + false);
            return false;
        }


        for (Eps eps : epsList) {
            for (HistoryPrice historyPrice : historyPriceList) {

                try {
                    boolean bool = eps.getPeriod().equals(historyPrice.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("刷新Pe列表结果:" + symbol + "----result:" + false);
                    return false;
                }


                if (eps.getPeriod().equals(historyPrice.getTime())) {
                    HistoryPrice hp = new HistoryPrice();
                    hp.setTime(historyPrice.getTime());
                    hp.setValue(historyPrice.getValue());
                    hp.setOpen(historyPrice.getOpen());
                    hp.setClose(historyPrice.getClose());
                    hp.setHigh(historyPrice.getHigh());
                    hp.setLow(historyPrice.getLow());
                    resultList.add(hp);


                    PERatio peRatio = new PERatio();
                    peRatio.setPeriod(historyPrice.getTime());
                    Double value = Double.parseDouble(historyPrice.getClose()) / Double.parseDouble(eps.getV());
                    String str = String.valueOf(value);
                    peRatio.setV(str);
                    peRatioList.add(peRatio);
//                    result = true;
                }
            }
        }
        stock.setPeRatios(peRatioList);
        result = true;
        stockUsInfoDoRepository.save(stock);
        log.info("刷新Pe列表结果:" + symbol + "----result:" + true);


        return true;
    }
}
