package com.fzc.fzcsearchneo4j.repository;

import com.fzc.fzcsearchneo4j.empty.StockIndustry;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 17:00
 */
public interface StockIndustryRepository  extends Neo4jRepository<StockIndustry,Long> {
}
