package com.fzc.fzcstockus.servcie.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.AsyncStockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2021/11/8 17:57
 */
@Service
@Slf4j
public class AsyncStockUsInfoServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements AsyncStockUsInfoService {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Override
    @Async
    public Future<StockUsInfoDo> asyncFindBySymbol(String symbol) {
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        return new AsyncResult<>(stockUsInfoDo);
    }

    @Override
    @Async
    public Future<StockUsInfoDo> asyncFindById(Integer id) {
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoById(id);
        return new AsyncResult<>(stockUsInfoDo);
    }

    @Override
    public List<StockUsInfoDo> asyncFindAllStockInfo() {

        return null;
    }
}
