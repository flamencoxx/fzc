package com.fzc.fzcstockus.servcie;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/22 11:39
 */
public interface ESSearchService {

    List<String> searchByKeyword(String keyword);

}
