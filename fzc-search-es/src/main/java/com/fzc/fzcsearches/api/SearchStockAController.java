package com.fzc.fzcsearches.api;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 17:29
 */
@Controller
@Slf4j
@CrossOrigin()
@RequestMapping("searchOptionA")
public class SearchStockAController {

    public static final int INT20 = 20;
    @Autowired
    private StockInfoService service;

    @Autowired
    private EsStockAImportRepository dao;


    @RequestMapping("aDetails")
    public ResponseEntity<JSONObject> aSearchOption(@RequestParam(value = "code") String key){
        JSONObject jsonObject = JSONUtil.createObj();
        JSONArray array = new JSONArray();
        key = key.trim();
        List<EsStockAimport> list = service.searchByKeyword(key);
        if (list.size() < INT20){
            array.addAll(list);
        }else{
            List<EsStockAimport> newList = list.subList(0, 20);
            array.addAll(newList);
        }
        jsonObject.put("result", array);
        return ResponseEntity.ok(jsonObject);
    }
}
