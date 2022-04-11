package com.fzc.fzcsearches.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 */
public class CacheUtil {


    public static Cache<Integer, String> testCache = CacheBuilder.newBuilder()
            .expireAfterWrite(100, TimeUnit.MINUTES)
            .build();


    public static final LoadingCache<Integer,String> loadCache = CacheBuilder.newBuilder()
            .expireAfterAccess(100, TimeUnit.MINUTES)
            .initialCapacity(100)
            .maximumSize(1000)
            .recordStats()
            .build(new CacheLoader<Integer, String>() {
                @Override
                public String load(Integer key) throws Exception {
                    return getRandomString(key);
                }
            });



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



}
