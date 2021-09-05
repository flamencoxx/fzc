package com.fzc.fzcstockus.consumer;

import com.fzc.fzcstockus.message.BasicFinancialMessage;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Flamenco.xxx
 * @date 2021/8/31 11:51
 */
@Component
@Slf4j
@RabbitListener(queues = BasicFinancialMessage.QUEUE)
public class BasicFinancialConsumer {

    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @RabbitHandler
    public void onMessage(BasicFinancialMessage message) {
        String symbol = message.getSymbol();
        stockBasicFinancialService.updateStockBasicFinancial(symbol);
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
