package com.fzc.fzcstockus.producer;

import com.fzc.fzcstockus.message.BasicFinancialMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author Flamenco.xxx
 * @date 2021/8/31 11:54
 */
@Component
public class BasicFinancialProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id,String symbol) {
        // 创建 Demo01Message 消息
        BasicFinancialMessage message = new BasicFinancialMessage();
        message.setId(id);
        message.setSymbol(symbol);
        // 同步发送消息
        rabbitTemplate.convertAndSend(BasicFinancialMessage.EXCHANGE, BasicFinancialMessage.ROUTING_KEY, message);
    }

    public void syncSend(String symbol) {
        // 创建 Demo01Message 消息
        BasicFinancialMessage message = new BasicFinancialMessage();
        message.setSymbol(symbol);
        // 同步发送消息
        rabbitTemplate.convertAndSend(BasicFinancialMessage.EXCHANGE, BasicFinancialMessage.ROUTING_KEY, message);
    }

    public void syncSendDefault(Integer id,String symbol) {
        // 创建 Demo01Message 消息
        BasicFinancialMessage message = new BasicFinancialMessage();
        message.setId(id);
        message.setSymbol(symbol);

        // 同步发送消息
        rabbitTemplate.convertAndSend(BasicFinancialMessage.QUEUE, message);
    }


    public void syncSendDefault(String symbol) {
        // 创建 Demo01Message 消息
        BasicFinancialMessage message = new BasicFinancialMessage();
        message.setSymbol(symbol);

        // 同步发送消息
        rabbitTemplate.convertAndSend(BasicFinancialMessage.QUEUE, message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id,String symbol) {
        try {
            // 发送消息
            this.syncSend(id,symbol);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

    @Async
    public ListenableFuture<Void> asyncSend(String symbol) {
        try {
            // 发送消息
            this.syncSend(symbol);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
