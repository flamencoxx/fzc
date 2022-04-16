package com.fzc.fzcstocka.repository;

import com.fzc.fzcstocka.model.FinanceBusinessComposition;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2022/4/14 9:20
 */
public interface FinanceBusinessCompositionRepository extends MongoRepository<FinanceBusinessComposition,String> {

    List<FinanceBusinessComposition> findFinanceBusinessCompositionByStockIdentity(String stockIdentity);
}
