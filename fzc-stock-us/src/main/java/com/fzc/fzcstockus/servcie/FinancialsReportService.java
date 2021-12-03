package com.fzc.fzcstockus.servcie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.reportedModel.StockUsReport;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 10:48
 */
public interface FinancialsReportService extends IService<StockUsReport> {

    /**
     * 根据股票代号跟新kryo序列化
     * @param code
     */
    void updateReport(String code);

    /**
     * 更新所有kryo序列化
     */
    void updateReports();

    /**
     * 根据股票代号更新fastjson序列化
     * @param code
     */
    void updateReportFast(String code);

    /**
     * 更新所有fastjson序列化
     */
    void updateReportsFast();

    /**
     * 更新stockUsReport数据包括fastjson和kryo两种序列化方式
     */
    void updateReportAll();

    /**
     * 查找repoert对象
     * @param code
     * @return
     */
    FinancialsReported getReportFast(String code);

    /**
     * 查找repoert对象
     * @param code
     * @return
     */
    FinancialsReported getReportKryo(String code);
}
