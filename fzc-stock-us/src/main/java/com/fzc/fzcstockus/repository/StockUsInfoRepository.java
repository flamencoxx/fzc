package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.model.StockUsInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11615
 */
public interface StockUsInfoRepository extends MongoRepository<StockUsInfo,Integer> {


}
