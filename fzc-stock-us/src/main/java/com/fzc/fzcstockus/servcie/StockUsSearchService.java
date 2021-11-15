package com.fzc.fzcstockus.servcie;

import com.google.common.collect.ListMultimap;

/**
 * @author admin
 */
public interface StockUsSearchService {


    ListMultimap<String,String> initializeNameAndDescription();

    void setSearchInfo();

    void setStockName();

    void initializeDescription();



}
