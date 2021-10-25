package com.fzc.fzccommon.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Flamenco.xxx
 * @date 2021/9/28 18:22
 */
@SpringBootTest
public class SearchAndSort {

    @Test
    public void testing() {

    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (m ==0 && n == 0){
            return;
        }


    }
}
