package com.fzc.fzcstocka.repository;

import com.fzc.fzcstocka.model.MarketSecuritiesInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 16:15
 */
//@Repository
    @Component
public interface StockAInfoRepository extends MongoRepository<MarketSecuritiesInfo, Long> {

    MarketSecuritiesInfo findMarketSecuritiesInfBySymbol(String name);

    MarketSecuritiesInfo findBySymbol(String symbol);
}
