package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author 11615
 */
public interface StockUsInfoDoRepository extends MongoRepository<StockUsInfoDo,Integer> {
    StockUsInfoDo findStockUsInfoDoById(Integer id);

    Page<StockUsInfoDo> findById(Integer id, Pageable pageable);

    Page<StockUsInfoDo> findByType(String type,Pageable pageable);

    Page<StockUsInfoDo> findByMic(String mic,Pageable pageable);

    Page<StockUsInfoDo> findBySymbolLike(String code,Pageable pageable);

    Page<StockUsInfoDo> findByDisplaySymbolLike(String code,Pageable pageable);


    Page<StockUsInfoDo> findBySymbolIsLike(String code,Pageable pageable);

    Page<StockUsInfoDo> findByMicAndSymbolLike(String mic,String code,Pageable pageable);

    StockUsInfoDo findStockUsInfoDoBySymbol(String symbol);

//    StockUsInfoDo findStockUsInfoDoBySymbol(String symbol);


}
