package com.fzc.fzcstocka.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstocka.mapper.StockAInfoMapper;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.StockAInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:34
 */
@Service
@Slf4j
public class StockAInfoServiceImpl  extends ServiceImpl<StockAInfoMapper, StockAInfo> implements StockAInfoService {

    @Override
    public StockAInfo searchByMoreKey(String code) {
        QueryWrapper<StockAInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("code",code)
                .or()
                .eq("symbol", code)
                .or()
                .eq("ts_code",code)
                .or()
                .eq("stock_identity",code);
        StockAInfo stock = null;
        try {
            stock = this.getOne(wrapper);
        } catch (Exception e) {
            log.error("请求的code为无效数据");
            e.printStackTrace();
        }
        return stock;
    }
}
