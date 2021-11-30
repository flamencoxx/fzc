package com.fzc.fzcsearches.api;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.model.StockUsImport;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.service.StockUsOtherService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author flamenco.xxx
 * @date 2021/11/24 14:50
 */
@Controller
@Slf4j
@CrossOrigin()
@RequestMapping("searchOptions")
public class SearchOptionsController {

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsOtherService stockUsOtherService;


    @RequestMapping("usDetails")
    public ResponseEntity<JSONObject> usSearchOptions(@RequestParam(value = "code") String code) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        code = code.toLowerCase();
        code = code.trim();
        List<String> list = Lists.newArrayList();
        int wordNum = SearchUtil.wordSum(code);
        if (stockUsImportService.checkIsSector(code)) {
            QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
            wrapper.like("sector", code)
                    .orderByDesc("market_value", "name");
            List<StockUsImport> stockList = stockUsImportService.list(wrapper);
            stockList.forEach(k -> list.add(k.getSymbol()));
        }
        List<EsStockUsImport> esList;
        if (wordNum == 1) {
            esList = stockUsImportService.searchByKeyword(code);
            esList.forEach(k -> list.add(k.getSymbol()));
        } else {
            esList = stockUsImportService.searchByKeywords(code);
            esList.forEach(k -> list.add(k.getSymbol()));
        }
        AtomicInteger limit = new AtomicInteger();
        if (list.size() == 0) {
            jsonObject.put("result", array);
            return ResponseEntity.ok(jsonObject);
        }

//        list.forEach(k -> {
//            if (limit.intValue() > strLimit) {
//                break;
//            }
//            limit.getAndIncrement();
//            JSONArray dataArray = new JSONArray();
//            dataArray.put(k);
//            array.put(dataArray);
//        });

        int strLimit = Math.min(list.size(), 20);

        for (int i = 0; i < strLimit; i++) {
            JSONArray dataArray = new JSONArray();
            dataArray.put(list.get(i));
            array.put(dataArray);
        }
        jsonObject.put("result", array);
        return ResponseEntity.ok(jsonObject);

    }

//    15ms

    @RequestMapping("usDetails2")
    public ResponseEntity<JSONObject> usSearchOptions2(@RequestParam(value = "code") String code) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        code = code.toLowerCase();
        code = code.trim();
        List<String> list = Lists.newLinkedList();
        Map<String,String> map = Maps.newLinkedHashMap();
        int wordNum = SearchUtil.wordSum(code);
        if (stockUsImportService.checkIsSector(code)) {
            QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
            wrapper.like("sector", code)
                    .orderByDesc("market_value", "name");
            List<StockUsImport> stockList = stockUsImportService.list(wrapper);
            stockList.forEach(k -> map.put(k.getSymbol(), k.getName()));
        }
        List<EsStockUsImport> esList;
        if (wordNum == 1) {
            esList = stockUsImportService.searchByKeyword(code);
            esList.forEach(k -> map.put(k.getSymbol(), k.getName()));
        } else {
            esList = stockUsImportService.searchByKeywords(code);
            esList.forEach(k -> map.put(k.getSymbol(), k.getName()));
        }
        if (map.size() == 0) {
            jsonObject.put("result", array);
            return ResponseEntity.ok(jsonObject);
        }
        int strLimit = Math.min(map.size(), 20);
        AtomicInteger num = new AtomicInteger();
        map.forEach((k,v) ->{
            if(num.get() < strLimit){
                JSONArray dataArray = new JSONArray();
                dataArray.put(k);
                dataArray.put(v);
                array.put(dataArray);
            }
            num.getAndIncrement();
        });


        jsonObject.put("result", array);
        return ResponseEntity.ok(jsonObject);

    }

    @RequestMapping("usDetails3")
    public ResponseEntity<JSONObject> usSearchOptions3(@RequestParam(value = "code") String code) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        code = code.toLowerCase();
        code = code.trim();
        List<String> list = Lists.newArrayList();
        int wordNum = SearchUtil.wordSum(code);
        if (stockUsImportService.checkIsSector(code)) {
            QueryWrapper<StockUsImport> wrapper = new QueryWrapper<StockUsImport>();
            wrapper.like("sector", code)
                    .orderByDesc("market_value", "name");
            List<StockUsImport> stockList = stockUsImportService.list(wrapper);
        }
        List<EsStockUsImport> esList;
        if (wordNum == 1) {
            esList = stockUsImportService.searchByKeyword(code);
        } else {
            esList = stockUsImportService.searchByKeywords(code);
        }
//        if (map.size() == 0) {
//            jsonObject.put("result", array);
//            return ResponseEntity.ok(jsonObject);
//        }
//        int strLimit = Math.min(map.size(), 20);
//        AtomicInteger num = new AtomicInteger();
//        map.forEach((k,v) ->{
//            if(num.get() < strLimit){
//                JSONArray dataArray = new JSONArray();
//                dataArray.put(k);
//                dataArray.put(v);
//                array.put(dataArray);
//            }
//            num.getAndIncrement();
//        });


        jsonObject.put("result", array);
        return ResponseEntity.ok(jsonObject);

    }

}
