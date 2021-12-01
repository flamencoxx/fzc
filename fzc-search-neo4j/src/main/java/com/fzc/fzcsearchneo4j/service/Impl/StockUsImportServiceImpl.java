package com.fzc.fzcsearchneo4j.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcsearchneo4j.empty.*;
import com.fzc.fzcsearchneo4j.mapper.StockUsImportMapper;
import com.fzc.fzcsearchneo4j.model.StockUsImport;
import com.fzc.fzcsearchneo4j.repository.*;
import com.fzc.fzcsearchneo4j.service.StockUsImportService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 11:06
 */
@Service
@Slf4j
public class StockUsImportServiceImpl extends ServiceImpl<StockUsImportMapper, StockUsImport> implements StockUsImportService{

    @Autowired
    private StockUsImportService stockUsImportService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockNameRepository stockNameRepository;

    @Autowired
    private StockDescriptionRepository stockDescriptionRepository;

    @Autowired
    private StockSectorRepository stockSectorRepository;

    @Autowired
    private StockIndustryRepository stockIndustryRepository;

    @Override
    public void initialize() {
        Map<String, StockSector> sectorMap = Maps.newHashMap();
        Map<String, StockIndustry> industryMap = Maps.newHashMap();
        stockRepository.deleteAll();
        stockNameRepository.deleteAll();
        stockDescriptionRepository.deleteAll();
        stockIndustryRepository.deleteAll();
        stockSectorRepository.deleteAll();
        List<StockUsImport> list = stockUsImportService.list();
        List<StockInfo> stockList = Lists.newArrayList();
        List<StockName> nameList = Lists.newArrayList();
        List<StockDescription> descriptionList = Lists.newArrayList();
        list.forEach(k -> {
            StockName name = StockName
                    .builder()
                    .name(k.getName())
                    .build();
            nameList.add(name);

            StockDescription description = StockDescription
                    .builder()
                    .description(k.getDescription())
                    .build();
            descriptionList.add(description);

            if (!sectorMap.containsKey(k.getSector())) {
                StockSector stockSector = StockSector
                        .builder()
                        .sector(k.getSector())
                        .build();
                sectorMap.put(k.getSector(), stockSector);
            }
            StockSector stockSector = sectorMap.get(k.getSector());

            if(!industryMap.containsKey(k.getIndustry())){
                StockIndustry stockIndustry = StockIndustry
                        .builder()
                        .industry(k.getIndustry())
                        .build();
                industryMap.put(k.getIndustry(),stockIndustry);
            }
            StockIndustry stockIndustry = industryMap.get(k.getIndustry());

            StockInfo info = StockInfo.builder()
                    .symbol(k.getSymbol())
                    .name(name)
                    .description(description)
                    .sector(stockSector)
                    .industry(stockIndustry)
                    .marketValue(k.getMarketValue())
                    .build();
            stockList.add(info);
        });
        List<StockSector> sectorList = Lists.newArrayList(sectorMap.values());
        List<StockIndustry> industryList = Lists.newArrayList(industryMap.values());
        stockRepository.saveAll(stockList);
        stockNameRepository.saveAll(nameList);
        stockDescriptionRepository.saveAll(descriptionList);
        stockSectorRepository.saveAll(sectorList);
        stockIndustryRepository.saveAll(industryList);
    }
}
