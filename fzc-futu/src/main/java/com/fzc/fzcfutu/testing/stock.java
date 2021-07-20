package com.fzc.fzcfutu.testing;

import com.futu.openapi.FTAPI;
import com.fzc.fzcfutu.tool.CapitalDistribution;
import com.fzc.fzcfutu.tool.Kl;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 11615
 */
public class stock {
    public static void main(String[] args) {
//        Kl qot = new Kl("002603", "SZ");
//        qot.start();
        CapitalDistribution qot1 = new CapitalDistribution("002603","SZ");
        qot1.start();
        try {
            Thread.sleep(1200 );
//            System.out.println(qot.result + "结束");
            Thread.sleep(1000 * 60 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
