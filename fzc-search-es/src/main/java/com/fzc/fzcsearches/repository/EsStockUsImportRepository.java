package com.fzc.fzcsearches.repository;

import com.fzc.fzcsearches.domain.EsStockUsImport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/15 17:29
 */
@Mapper
@Repository
public interface EsStockUsImportRepository  extends ElasticsearchRepository<EsStockUsImport, Long> {

    List<EsStockUsImport> findEsStockUsImportsByDescriptionOrNameOrSymbol(String key1,String key2,String key3);

    List<EsStockUsImport> findEsStockUsImportsByDescriptionAndNameAndSymbol(String description, String name,String symbol);

    List<EsStockUsImport> findEsStockUsImportsBySymbol(String symbol);

    List<EsStockUsImport> findEsStockUsImportsByDescriptionOrNameIsLikeOrSymbolIsLike(String key1,String key2,String key3);


}
