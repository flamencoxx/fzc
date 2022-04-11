package com.fzc.fzcsearches;

import cn.hutool.core.lang.Console;
import com.fzc.fzcsearches.util.CacheUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class GuavaTest {

    @Test
    public void test1() {
        Console.log(CacheUtil.testCache.getIfPresent(1));
        Integer[] arr = {1, 2, 3};
        List<Integer> list = Lists.newArrayList(arr);
        ImmutableMap<Integer, String> map = CacheUtil.testCache.getAllPresent(list);
        ImmutableSet<Map.Entry<Integer, String>> set = map.entrySet();
        set.forEach(k -> {
            Console.log(CacheUtil.testCache.getIfPresent(k.getKey()));
        });
    }

    //计算两个数之间的奇数的个数
    @Test
    public void test5() {
        int count = 0;
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 1) {
                count++;
            }
        }
        Console.log(count);
    }

    @Test
    public void test2() {
        int i = 1;
        while (i < 20) {
            try {
                String res = CacheUtil.loadCache.get(i);
                Console.log(i + ":" + res);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            i++;
        }
        for(int j = 1;j < 5;j++){
            try {
                Console.log(CacheUtil.loadCache.get(j));
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Console.log(CacheUtil.loadCache.getIfPresent(1));
        }
        Console.log(CacheUtil.loadCache.stats().toString());

    }


    @Test
    public void test3(){
        String res = null;
        try {
            res = CacheUtil.loadCache.get(19);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Console.log(res);
    }

}
