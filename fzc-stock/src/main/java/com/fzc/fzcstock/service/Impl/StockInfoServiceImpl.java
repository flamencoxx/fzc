package com.fzc.fzcstock.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fzc.fzcstock.mapper.StockInfoMapper;
import com.fzc.fzcstock.model.StockInfo;
import com.fzc.fzcstock.service.StockInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 11615
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockInfoService {
}
