package com.fzc.fzcsearchneo4j.repository;

import com.fzc.fzcsearchneo4j.empty.StockSector;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 16:59
 */
public interface StockSectorRepository  extends Neo4jRepository<StockSector,Long> {
}
