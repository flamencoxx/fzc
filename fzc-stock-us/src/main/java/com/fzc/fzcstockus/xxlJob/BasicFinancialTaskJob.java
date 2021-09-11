package com.fzc.fzcstockus.xxlJob;

import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mutiThread.FetchBasicFinancial;
import com.fzc.fzcstockus.mutiThread.FetchCompanyInfo;
import com.fzc.fzcstockus.producer.BasicFinancialProducer;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.servcie.StockUsHistoryTableService;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Flamenco.xxx
 * @date 2021/9/6 11:42
 */
@Component
@Slf4j
public class BasicFinancialTaskJob {

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Autowired
    private StockCompanyInfoService stockCompanyInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private FetchBasicFinancial fetchBasicFinancial;

    @Autowired
    private FetchCompanyInfo fetchCompanyInfo;

    @Autowired
    private StockUsHistoryTableService stockUsHistoryTableService;

    @Autowired
    private BasicFinancialProducer basicFinancialProducer;

    private final AtomicInteger counts = new AtomicInteger();

    @XxlJob("fzc-us-basic")
    public ReturnT<String> execute(String param) throws Exception {

        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        // 打印日志
        log.info("fzc-[execute][定时第 ({}) 次执行],执行时间:[{}]", counts.incrementAndGet(), sdf.format(time));
        // 返回执行成功

        int num = 4757;

        for (int i = 1; i < num; i++) {
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(i);
            String symbol = stock.getSymbol();
            basicFinancialProducer.asyncSend(i, symbol);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        log.info("xxl--->(fzc-us-basic),美股BasicFinancial数据刷新定时刷新完成");
        return ReturnT.SUCCESS;
    }
}
