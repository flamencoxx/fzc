package com.fzc.fzcstockus.RabbitMQ;

import com.fzc.fzcstockus.producer.BasicFinancialProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author Flamenco.xxx
 * @date 2021/8/31 12:02
 */

@SpringBootTest
@Slf4j
public class BasicTesting {

    @Autowired
    private BasicFinancialProducer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        String symbol = "AAPL";
        producer.syncSend(id,symbol);
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
