package com.fzc.fzcsearchneo4j.simpleTesting;

import com.fzc.fzcsearchneo4j.empty.*;
import com.fzc.fzcsearchneo4j.model.StockUsImport;
import com.fzc.fzcsearchneo4j.repository.*;
import com.fzc.fzcsearchneo4j.service.StockUsImportService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 10:53
 */
@SpringBootTest
public class testing {

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

    @Test
    public void testing() {
        List<StockUsImport> list = stockUsImportService.list();
        list.forEach(k -> {
            System.out.println(k.getSymbol());
        });
    }

//    @Test
//    public void create(){
//        List<StockUsImport> list = stockUsImportService.list();
//        List<StockInfo> stockList = Lists.newArrayList();
//        list.forEach(k->{
//            StockInfo stockInfo = StockInfo
//                    .builder()
//                    .symbol(k.getSymbol())
//                    .name(k.getName())
//                    .description(k.getDescription())
//                    .sector(k.getSector())
//                    .industry(k.getIndustry())
//                    .marketValue(k.getMarketValue())
//                    .build();
//            stockList.add(stockInfo);
//            System.out.println(k.getId());
//        });
//        stockRepository.saveAll(stockList);
//    }

    @Test
    public void testing2() {
//        System.out.println("test");
//        stockRepository.deleteAll();
        List<StockInfo> list = stockRepository.findAll();
        System.out.println(list.size());
    }

    @Test
    public void creat2() {
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

    @Test
    public void listAll() {
        stockRepository.deleteAll();
        stockNameRepository.deleteAll();
        stockDescriptionRepository.deleteAll();
        List<StockInfo> list = stockRepository.findAll();
        System.out.println(list.size());
    }

    @Test
    public void test1() {
        List<StockDescription> list = stockDescriptionRepository.findAll();
        List<StockInfo> list1 = stockRepository.findAll();
        List<StockName> listname = stockNameRepository.findAll();
        System.out.println(list1.size());
        System.out.println(list.size());
        System.out.println(listname.size());
    }
}
