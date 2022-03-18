package com.fzc.fzcsearches.config;

import com.fzc.fzcsearches.service.StockInfoService;
import com.fzc.fzcsearches.service.StockUsImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:59
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockInfoService stockInfoService;
//    初始化Redis

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            stockUsImportService.importAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int importAll = stockInfoService.importAll();
        int importSectorRes = stockUsImportService.importSectorToRedis();
        int importIndustryRes = stockUsImportService.importIndustryToRedis();
        int importMarketValueRes = stockUsImportService.importMarketValue();
    }
}
