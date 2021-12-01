package com.fzc.fzcsearchneo4j.repository;

import com.fzc.fzcsearchneo4j.empty.StockName;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 15:53
 */
public interface StockNameRepository extends Neo4jRepository<StockName,Long> {
}
