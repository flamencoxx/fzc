package com.fzc.fzcsearches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.model.StockUsImport;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/9 14:44
 */
public interface StockUsImportService extends IService<StockUsImport> {

    int importSectorToRedis();

    int importIndustryToRedis();

    int importAll();

    int importMarketValue();

    List<EsStockUsImport> searchByKeyword(String keyword);

    List<EsStockUsImport> searchByKeywords(String keywords);

    boolean checkIsSector(String key);

    boolean checkIsIndustry(String key);

    List<String> sortByValues(List<String> list);


}
