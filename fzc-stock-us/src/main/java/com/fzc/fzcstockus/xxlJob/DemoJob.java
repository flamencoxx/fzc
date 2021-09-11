package com.fzc.fzcstockus.xxlJob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Flamenco.xxx
 */
@Component
public class DemoJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger counts = new AtomicInteger();

    @XxlJob("fzc-test")
    public ReturnT<String> execute(String param) throws Exception {

        Date time =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");




        // 打印日志
        logger.info("fzc-[execute][定时第 ({}) 次执行],执行时间:[{}]", counts.incrementAndGet(),sdf.format(time));



        // 返回执行成功
        return ReturnT.SUCCESS;
    }

}
