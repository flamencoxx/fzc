package com.fzc.fzcstockus.repository;

import com.fzc.fzcstockus.DO.StockUsDO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author 11615
 */
public interface StockUsDORepository extends MongoRepository<StockUsDO,Integer> {

    List<StockUsDO> findByMicEquals(String mic);

    List<StockUsDO> findByMicEqualsAndTypeEquals(String mic,String type);
}
