package com.fzc.fzcfutu.testing;

import com.fzc.fzcfutu.tool.CapitalDistribution;
import com.fzc.fzcfutu.tool.Kl;

public class KlTesting {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        Kl qot1 = new Kl("002603","SZ");
        qot1.start();
        try {
            long end = System.currentTimeMillis();
            Thread.sleep(1200 );
            System.out.println(end - startTime - 1200);
            System.out.println(qot1.result + "结束");
            Thread.sleep(1000 * 60 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
