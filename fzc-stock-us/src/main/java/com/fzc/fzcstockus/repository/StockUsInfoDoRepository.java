package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.config.RedisConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

//
//操作mongoDB数据库重要的类，操作stock_us_important表。
//

/**
 * @author 11615
 */
public interface StockUsInfoDoRepository extends MongoRepository<StockUsInfoDo,Integer> {

    StockUsInfoDo findStockUsInfoDoById(Integer id);

    Page<StockUsInfoDo> findById(Integer id, Pageable pageable);

    Page<StockUsInfoDo> findByType(String type,Pageable pageable);

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'Us_stock:findByMic:'+#symbol", unless = "#result==null")
    Page<StockUsInfoDo> findByMic(String mic,Pageable pageable);

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'Us_stock:findBySymbolLike:'+#symbol", unless = "#result==null")
    Page<StockUsInfoDo> findBySymbolLike(String code,Pageable pageable);

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'Us_stock:findByDisplaySymbolLike:'+#symbol", unless = "#result==null")
    Page<StockUsInfoDo> findByDisplaySymbolLike(String code,Pageable pageable);


    Page<StockUsInfoDo> findBySymbolIsLike(String code,Pageable pageable);

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'Us_stock:findByMicAndSymbolLike:'+#symbol", unless = "#result==null")
    Page<StockUsInfoDo> findByMicAndSymbolLike(String mic,String code,Pageable pageable);

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'Us_stock:findStockUsInfoDoBySymbol:'+#symbol", unless = "#result==null")
    StockUsInfoDo findStockUsInfoDoBySymbol(String symbol);

//    StockUsInfoDo findStockUsInfoDoBySymbol(String symbol);

    Boolean existsBySymbol(String symbol);


}
