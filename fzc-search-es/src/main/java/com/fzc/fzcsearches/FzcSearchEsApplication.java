package com.fzc.fzcsearches;

import com.fzc.fzcsearches.service.StockUsImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Flamenco
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class FzcSearchEsApplication {

    @Autowired
    private StockUsImportService stockUsImportService;

    public static void main(String[] args) {
        SpringApplication.run(FzcSearchEsApplication.class, args);
    }
    int importSectorRes = stockUsImportService.importSectorToRedis();
    int importIndustryRes = stockUsImportService.importIndustryToRedis();
    int importMarketValueRes = stockUsImportService.importMarketValue();
}
