package com.fzc.fzcsearches.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcsearches.domain.EsStockAimport;
import com.fzc.fzcsearches.mapper.StockAInfoMapper;
import com.fzc.fzcsearches.model.StockAInfo;
import com.fzc.fzcsearches.repository.EsStockAImportRepository;
import com.fzc.fzcsearches.service.StockAInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:34
 */
@Service
@Slf4j
public class StockAInfoServiceImpl extends ServiceImpl<StockAInfoMapper, StockAInfo> implements StockAInfoService {

    @Autowired
    private EsStockAImportRepository esDao;

    @Autowired
    private StockAInfoService service;

    @Override
    public int importAll() {
        List<StockAInfo> stockList = service.list();
        List<EsStockAimport> esList = Lists.newLinkedList();
        stockList.forEach(st -> {
            EsStockAimport es = new EsStockAimport();
            BeanUtil.copyProperties(st, es);
            esList.add(es);
        });
        Iterable<EsStockAimport> esIt = esDao.saveAll(esList);
        Iterator<EsStockAimport> it = esIt.iterator();
        int res = 0;
        while(it.hasNext()){
            res++;
            it.next();
        }
        return res;
    }
}
