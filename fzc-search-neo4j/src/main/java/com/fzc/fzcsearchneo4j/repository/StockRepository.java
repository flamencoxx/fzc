package com.fzc.fzcsearchneo4j.repository;

import com.fzc.fzcsearchneo4j.empty.StockInfo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 11:24
 */
@Repository
public interface StockRepository extends Neo4jRepository<StockInfo,String> {

}
