package com.fzc.fzcsearchneo4j.repository;

import com.fzc.fzcsearchneo4j.empty.StockDescription;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 16:35
 */
public interface StockDescriptionRepository extends Neo4jRepository<StockDescription,Long> {
}
