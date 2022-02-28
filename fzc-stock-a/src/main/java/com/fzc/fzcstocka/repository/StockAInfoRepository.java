package com.fzc.fzcstocka.repository;

import com.fzc.fzcstocka.model.MarketSecuritiesInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 16:15
 */
//@Repository
//    @Component
public interface StockAInfoRepository extends MongoRepository<MarketSecuritiesInfo, String> {

    MarketSecuritiesInfo findMarketSecuritiesInfBySymbol(String name);

    MarketSecuritiesInfo findBySymbol(String symbol);

    /**
     * 判断数据库中是否存在这个数据
     * @param code
     * @param symbol
     * @param stockIdentity
     * @param tsCode
     * @return
     */
    boolean existsByCodeOrSymbolOrStockIdentityOrTsCode(String code,String symbol,String stockIdentity,String tsCode);


}
