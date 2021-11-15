package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsImportMapper;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.servcie.StockUsImportService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/9 15:08
 */
@SpringBootTest
public class TestStockUsImport {


    @Autowired
    private StockUsImportService service;

    @Autowired
    private StockUsImportMapper stockUsImportMapper;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Test
    public void test1(){
        StockUsImport stock = stockUsImportMapper.selectById(1);
        System.out.println(stock.toString());
    }

    @Test
    public void test2(){
        Long startDate = System.currentTimeMillis();
        List<StockUsInfoDo> list = stockUsInfoDoRepository.findAll();
        Long EndDate = System.currentTimeMillis();
        System.out.println(startDate - EndDate);
    }

    @Test void test3(){
        Long startDate = System.currentTimeMillis();
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(1);
        Long EndDate = System.currentTimeMillis();
        System.out.println(startDate - EndDate);
        System.out.println(stock);
    }

    @Test void test4(){
        for(int i = 4369;i < 4758;i++){
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(i);
            Integer id = stock.getId();
            String symbol = stock.getSymbol();
            StockUsInfoDo.CompanyOverview co = null;
            try {
                co = stock.getCompanyOverview();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String name = null;
            try {
                name = co.getName();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String description = null;
            try {
                description = co.getDescription();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            String sector = co.getSector();
            String industry = co.getIndustry();
            String marketValue = co.getMarketCapitalization();
            StockUsImport stockUsImport = new StockUsImport();
            stockUsImport.setId(id);
            stockUsImport.setSymbol(symbol);
            stockUsImport.setName(name);
            stockUsImport.setDescription(description);
            stockUsImport.setSector(sector);
            stockUsImport.setIndustry(industry);
            stockUsImport.setMarketValue(marketValue);
            boolean res = stockUsImportService.save(stockUsImport);
            System.out.println("股票id:" + id + "_____结果:" + res);
        }
    }
}
