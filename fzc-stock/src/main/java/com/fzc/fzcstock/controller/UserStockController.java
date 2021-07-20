package com.fzc.fzcstock.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcstock.mapper.UserStockMapper;
import com.fzc.fzcstock.model.UserStock;
import com.fzc.fzcstock.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 11615
 */
@Controller
@RequestMapping(value="/userStock")
public class UserStockController {

    @Autowired
    private UserStockService userStockService;

    @Autowired
    private UserStockMapper userStockMapper;


    @GetMapping("findUserStock")
    public ResponseEntity<List<UserStock>> findUserStockList(@RequestParam(value="username") String stockUser){
        List<UserStock> stockList = new ArrayList<>();
        QueryWrapper<UserStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stock_user", stockUser);
        stockList = userStockService.list(queryWrapper);
        return ResponseEntity.ok(stockList);
    }

    @PostMapping("insert")
    public ResponseEntity<Boolean> insert(@RequestBody UserStock userStock){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        userStock.setTime(sdf.format(date));
        userStock.setStockCreatTime(sdf.format(date));
        boolean result = userStockService.save(userStock);
        if (result){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("allList")
    public ResponseEntity<List<UserStock>> findAll(){
        List<UserStock> allList = new ArrayList<UserStock>();
        allList = userStockService.list();
        return ResponseEntity.ok(allList);
    }
}
