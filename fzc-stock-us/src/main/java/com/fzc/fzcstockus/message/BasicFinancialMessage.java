package com.fzc.fzcstockus.message;

import java.io.Serializable;

/**
 * @author Flamenco.xxx
 * @date 2021/8/31 11:41
 */
public class BasicFinancialMessage implements Serializable {

    public static final String QUEUE = "QUEUE_BasicFinancial";

    public static final String EXCHANGE = "EXCHANGE_BasicFinancial";

    public static final String ROUTING_KEY = "ROUTING_BasicFinancial";

    private Integer id;

    private String symbol;

    public BasicFinancialMessage(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public BasicFinancialMessage(Integer id) {
        this.id = id;
    }

    public BasicFinancialMessage(String symbol) {
        this.symbol = symbol;
    }

    public BasicFinancialMessage() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasicFinancialMessage{");
        sb.append("id=").append(id);
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
