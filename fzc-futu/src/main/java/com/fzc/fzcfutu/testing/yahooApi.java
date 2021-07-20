package com.fzc.fzcfutu.testing;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class yahooApi {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Stock stock = null;
        try {
            stock = YahooFinance.get("INTC");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        stock.print();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        Stock tesla = null;
        try {
            tesla = YahooFinance.get("TSLA", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert tesla != null;
            System.out.println(tesla.getHistory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
