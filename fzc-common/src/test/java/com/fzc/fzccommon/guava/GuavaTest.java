package com.fzc.fzccommon.guava;

import cn.hutool.core.lang.Console;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 */
public class GuavaTest {
    class Payment{
        private int id;
        private String name;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Payment{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    private static Cache<Integer, Payment> paymentCache = CacheBuilder.newBuilder()
            .expireAfterWrite(100, TimeUnit.MINUTES)
            .build();

    @Test
    public void test(){
        int i = 1;
        Set<Payment> set = Sets.newHashSet();
        while(i < 20){
            Payment payment = new Payment();
            payment.setId(i);
            payment.setName(getRandomString(5));
            payment.setAddress(getRandomString(10));
            set.add(payment);
            i++;
        }
        set.forEach(k->{
            paymentCache.put(k.getId(),k);
        });
        Console.log(paymentCache.getIfPresent(1).toString());
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1(){
        Integer i = 1;
        Console.log(paymentCache.getIfPresent(1).toString());
    }
}

