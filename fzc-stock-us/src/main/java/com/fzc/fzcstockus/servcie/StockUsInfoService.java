package com.fzc.fzcstockus.servcie;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.StockUsInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 11615
 */
public interface StockUsInfoService extends IService<StockUsInfo> {

    void insertCompanyOverview(int start, int end);

    void updatePeers(int start, int end);

    void updateBasicFinancial(int start,int end);

    JSONObject searchUsHistoryDay(String code);

    List<StockUsInfoDo> findAllByPage(int page,int size,String sort);

    List<StockUsInfoDo> findByMicPage(int page,int size,String mic, String sort);
}
