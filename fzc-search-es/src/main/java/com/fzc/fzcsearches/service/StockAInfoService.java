package com.fzc.fzcsearches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearches.model.StockAInfo;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:30
 */

public interface StockAInfoService extends IService<StockAInfo> {

    int importAll();
}
