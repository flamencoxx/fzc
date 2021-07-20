package com.fzc.fzcstockus.controller;

/**
 * @author Flamenco.xxx
 * @date 2021/6/25 10:03
 */

import cn.hutool.json.JSONObject;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin()
@Controller
@RequestMapping(value ="/UsHistory")
public class StockUsHistoryController {

    @Autowired
    private StockUsInfoService stockUsInfoService;




    
    @GetMapping("UsDayHistory")
    public ResponseEntity<JSONObject> searchUsDayHistory(@RequestParam(value="code")String code){
        JSONObject stockJson = stockUsInfoService.searchUsHistoryDay(code);
        if (stockJson == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(stockJson);
    };

}
