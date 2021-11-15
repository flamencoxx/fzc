package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.producer.BasicFinancialProducer;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author flamenco
 */
@Service
@Slf4j
public class StockUsSearchServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockUsSearchService {
    @Autowired
    private StockUsInfoService stockUsInfoService;
    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;
    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;
    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;
    @Autowired
    private FetchBasicFinancial fetchBasicFinancial;
    @Autowired
    private FetchCompanyInfo fetchCompanyInfo;
    @Autowired
    private StockUsHistoryTableService stockUsHistoryTableService;
    @Autowired
    private BasicFinancialProducer basicFinancialProducer;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;
    @Value("${redis.key.stockName}")
    private String REDIS_KEY_STOCK_NAME;
    @Value("${redis.key.StockDescription}")
    private String REDIS_KEY_STOCK_DESCRIPTION;


    @Override
    public ListMultimap<String, String> initializeNameAndDescription() {
        ListMultimap<String, String> resList = MultimapBuilder.hashKeys().arrayListValues().build();
        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        builder.add("Inc")
                .add("Company")
                .add("Corporation");
        ImmutableSet<String> ignoredSet = builder.build();

        return resList;
    }

    @Override
    public void setStockName() {

    }

    @Override
    public void initializeDescription() {

    }


    @Override
    public void setSearchInfo() {

    }

}
