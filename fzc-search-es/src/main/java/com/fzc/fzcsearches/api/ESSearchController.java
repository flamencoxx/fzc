package com.fzc.fzcsearches.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/22 10:48
 */
@RestController
@RequestMapping("/ESSearch")
public class ESSearchController {

    @Autowired
    private StockUsImportService stockUsImportService;

    @RequestMapping(value = "/search")
    public List<String> searchByKeyword(@RequestParam("keyword") String keyword){
        keyword = keyword.trim();
        keyword = keyword.toLowerCase();
        List<String> list = Lists.newArrayList();
        int wordNum = SearchUtil.wordSum(keyword);
        if(stockUsImportService.checkIsSector(keyword)){
            QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
            wrapper.like("sector",keyword)
                    .orderByDesc("market_value","name");
            List<StockUsImport> stockList = stockUsImportService.list(wrapper);
            stockList.forEach(k -> list.add(k.getSymbol()));
            return list;
        }
        List<EsStockUsImport> esList;
        if(wordNum == 1){
            esList = stockUsImportService.searchByKeyword(keyword);
            esList.forEach(k -> list.add(k.getSymbol()));
        }else{
            esList = stockUsImportService.searchByKeywords(keyword);
            esList.forEach(k -> list.add(k.getSymbol()));
        }
        return list;

    }

    @RequestMapping(value = "/checkIsSector")
    public boolean checkIsSector(@RequestParam("keyword") String keyword){
        return stockUsImportService.checkIsSector(keyword);
    }



}
