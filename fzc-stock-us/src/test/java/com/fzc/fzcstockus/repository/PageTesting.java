package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/6/26 10:05
 */
@SpringBootTest
public class PageTesting {




    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Test
    public void pageTest1(){
        int id = 1;
        int page = 0;  // 查詢頁數，從0開始為第一頁
        int size = 10; // 每頁筆數，設為每頁10筆

        PageRequest pageable = PageRequest.of(page, size, Sort.by("symbol").ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findAll(pageable);

        System.out.println(pageResult.getTotalPages()); // 1 全部頁數
        System.out.println(pageResult.getTotalElements()); // 1 全部筆數
        System.out.println(pageResult.getSize()); // 10 每頁筆數
        System.out.println(pageResult.getNumber()); // 0 目前頁號，0為第一頁
        System.out.println(pageResult.getNumberOfElements()); // 1 目前頁筆數


        List<StockUsInfoDo> stockUsInfoDoList = pageResult.toList();

        for(StockUsInfoDo s: stockUsInfoDoList){
            String symbol = s.getSymbol();
            System.out.println(symbol);
        }
    }

    @Test
    public void pageTest2(){
        String mic = "XNYS";
        int page = 0;  // 查詢頁數，從0開始為第一頁
        int size = 10; // 每頁筆數，設為每頁10筆

        PageRequest pageable = PageRequest.of(page, size, Sort.by("symbol").ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findByMic(mic,pageable);

        System.out.println(pageResult.getTotalPages()); // 1 全部頁數
        System.out.println(pageResult.getTotalElements()); // 1 全部筆數
        System.out.println(pageResult.getSize()); // 10 每頁筆數
        System.out.println(pageResult.getNumber()); // 0 目前頁號，0為第一頁
        System.out.println(pageResult.getNumberOfElements()); // 1 目前頁筆數

//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = null; // 轉成json字串
//        try {
//            jsonString = mapper.writeValueAsString(pageResult.getContent());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        List<StockUsInfoDo> stockUsInfoDoList = pageResult.toList();

        for(StockUsInfoDo s: stockUsInfoDoList){
            String symbolStr = s.getSymbol();
            String micStr = s.getMic();
            System.out.println(symbolStr+ ":" + micStr);
        }
    }


    @Test
    public void pageTest3(){
        int id = 1;
        int page = 0;  // 查詢頁數，從0開始為第一頁
        int size = 30; // 每頁筆數，設為每頁10筆
        String code = "IB";

        PageRequest pageable = PageRequest.of(page, size, Sort.by("symbol").ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findByDisplaySymbolLike(code,pageable);

        System.out.println(pageResult.getTotalPages()); // 1 全部頁數
        System.out.println(pageResult.getTotalElements()); // 1 全部筆數
        System.out.println(pageResult.getSize()); // 10 每頁筆數
        System.out.println(pageResult.getNumber()); // 0 目前頁號，0為第一頁
        System.out.println(pageResult.getNumberOfElements()); // 1 目前頁筆數


        List<StockUsInfoDo> stockUsInfoDoList = pageResult.toList();

        for(StockUsInfoDo s: stockUsInfoDoList){
            String symbol = s.getSymbol();
            System.out.println(symbol);
        }
    }


    @Test
    public void pageTest4(){
        String mic = "XNYS";
        int page = 0;  // 查詢頁數，從0開始為第一頁
        int size = 10; // 每頁筆數，設為每頁10筆

        PageRequest pageable = PageRequest.of(page, size, Sort.by("symbol").ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findByMic(mic,pageable);

        System.out.println(pageResult.getTotalPages()); // 1 全部頁數
        System.out.println(pageResult.getTotalElements()); // 1 全部筆數
        System.out.println(pageResult.getSize()); // 10 每頁筆數
        System.out.println(pageResult.getNumber()); // 0 目前頁號，0為第一頁
        System.out.println(pageResult.getNumberOfElements()); // 1 目前頁筆數

//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = null; // 轉成json字串
//        try {
//            jsonString = mapper.writeValueAsString(pageResult.getContent());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        List<StockUsInfoDo> stockUsInfoDoList = pageResult.toList();

        for(StockUsInfoDo s: stockUsInfoDoList){
            String symbolStr = s.getSymbol();
            String micStr = s.getMic();
            System.out.println(symbolStr+ ":" + micStr);
        }
    }
}
