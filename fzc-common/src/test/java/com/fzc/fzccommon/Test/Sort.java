package com.fzc.fzccommon.Test;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/12/8 9:39
 */
@SpringBootTest
public class Sort {

    @Test
    public void sort(){
        int[] nums = {3,4,1,2,7,6};
        List<Integer> list = Lists.newArrayList(3,4,1,2,7,6);
        list.sort((Integer o1, Integer o2) -> {
            return o1.compareTo(o2);
        });
        System.out.println(list);
    }

    @Test
    public void sort1(){
        int[] nums = {3,4,1,2,7,6};
        List<Integer> list = Lists.newArrayList(3,4,1,2,7,6);
        list.sort(Integer::compareTo);
        System.out.println(list);
    }

    @Test
    public void sort2(){
        int[] nums = {3,4,1,2,7,6};
        List<Integer> list = Lists.newArrayList(3,4,1,2,7,6);
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }
}
