package com.fzc.fzcstocka.util;

import com.fzc.fzcstocka.model.ResultAnalyzer;

import java.util.Comparator;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 10:58
 */
public class ResultSortUtil {

    List<ResultAnalyzer> sortResultList(List<ResultAnalyzer> list){
        list.sort(Comparator.comparing(ResultAnalyzer::getPeriod, (t1, t2) -> t2.compareTo(t1)));
        return list;
    }
}
