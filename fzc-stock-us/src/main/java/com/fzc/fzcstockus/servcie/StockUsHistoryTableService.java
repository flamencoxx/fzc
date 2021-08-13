package com.fzc.fzcstockus.servcie;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.model.StockUsInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/8/12 12:52
 */
public interface StockUsHistoryTableService extends IService<StockUsInfo> {

    public JSONObject findUsTable(String code);

}
