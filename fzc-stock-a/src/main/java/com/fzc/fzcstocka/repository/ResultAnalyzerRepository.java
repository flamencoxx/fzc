package com.fzc.fzcstocka.repository;

import com.fzc.fzcstocka.model.ResultAnalyzer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 10:26
 */
public interface ResultAnalyzerRepository extends MongoRepository<ResultAnalyzer,String> {

    List<ResultAnalyzer> findByAnalyzerAndStockIdentity(String analyzer,String stockIdentity);

    List<ResultAnalyzer> findByStockIdentityAndAnalyzer(String stockIdentity,String analyzer);

    List<ResultAnalyzer> findByStockIdentity(String stockIdentity);
}
