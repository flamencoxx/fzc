package com.fzc.fzcstocka.service;

import com.fzc.fzcstocka.model.FinanceBusinessComposition;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Flamenco.xxx
 * @date 2022/4/14 9:32
 */
public interface FinanceBizCompositionService {

    List<FinanceBusinessComposition> getComposition(String stockCode);

    Future<List<FinanceBusinessComposition>> AsyncGetComposition(String stockCode);
}
