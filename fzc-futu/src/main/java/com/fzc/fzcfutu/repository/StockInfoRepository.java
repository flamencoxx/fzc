package com.fzc.fzcfutu.repository;

import com.fzc.fzcfutu.DO.StockInfoDO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11615
 */
public interface StockInfoRepository extends MongoRepository<StockInfoDO,Integer> {

    StockInfoDO findStockInfoDOById(Integer id);

    StockInfoDO findStockInfoDOBySymbol(String symbol);

    StockInfoDO findStockInfoDOByName(String name);



}
