package com.fzc.fzcsearches.repository;

import com.fzc.fzcsearches.domain.EsStockAimport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 15:29
 */
@Mapper
@Repository
public interface EsStockAImportRepository extends ElasticsearchRepository<EsStockAimport,Long> {
}
