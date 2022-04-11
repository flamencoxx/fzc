package com.fzc.fzcstocka.service;

import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.model.ScoreInfo;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2022/2/24 9:46
 */
public interface ResultService {

    /**
     * 现金流肖像
     * @param code
     * @return
     */
    List<ResultAnalyzer> getCfList(String code);

    /**
     *货币资金分析
     * @param code
     * @return
     */
    List<ResultAnalyzer> getMfList(String code);

    /**
     *应收预付分析
     * @param code
     * @return
     */
    List<ResultAnalyzer> getPrList(String code);

    /**
     * 资产构成分析
     * @param code
     * @return
     */
    List<ResultAnalyzer> getAcList(String code);

    /**
     * 利润表分析
     * @param code
     * @return
     */
    List<ResultAnalyzer> getIsList(String code);

    /**
     * All of it
     * @param code
     * @return
     */
    List<ResultAnalyzer> getAllList(String code);


    ScoreInfo getComprehensiveCode(String code);

    Future<ScoreInfo> AsyncGetComprehensiveCode(String code);

}
