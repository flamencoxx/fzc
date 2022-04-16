package com.fzc.fzcstocka.service.Impl;

import com.fzc.fzcstocka.model.FinanceBusinessComposition;
import com.fzc.fzcstocka.repository.FinanceBusinessCompositionRepository;
import com.fzc.fzcstocka.service.FinanceBizCompositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Flamenco.xxx
 * @date 2022/4/14 9:32
 */
@Service
@Slf4j
public class FinanceBizCompositionServiceImpl implements FinanceBizCompositionService {

    @Autowired
    private FinanceBusinessCompositionRepository financeBusinessCompositionRepository;

    @Override
    public List<FinanceBusinessComposition> getComposition(String stockCode) {
        List<FinanceBusinessComposition> financeBusinessCompositions = financeBusinessCompositionRepository.findFinanceBusinessCompositionByStockIdentity(stockCode);
        return financeBusinessCompositions;
    }

    @Override
    @Async
    public Future<List<FinanceBusinessComposition>> AsyncGetComposition(String stockCode) {
        List<FinanceBusinessComposition> financeBusinessCompositions = this.getComposition(stockCode);
        return new AsyncResult<>(financeBusinessCompositions);
    }
}
