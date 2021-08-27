package com.fzc.fzccommon.Algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/21 17:28
 */
@SpringBootTest
public class CombinationSumII {

    @Test
    public void test(){
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum2(nums, target);
        System.out.println(result);

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(new ArrayList<>(), combinations, new boolean[candidates.length], 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              boolean[] hasVisited, int start, int target, final int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != 0 && candidates[i] == candidates[i - 1] && !hasVisited[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                hasVisited[i] = true;
                backtracking(tempCombination, combinations, hasVisited, i + 1, target - candidates[i], candidates);
                hasVisited[i] = false;
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }
}
