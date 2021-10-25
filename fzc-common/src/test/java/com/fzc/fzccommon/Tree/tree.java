package com.fzc.fzccommon.Tree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author Flamenco.xxx
 * @date 2021/9/25 16:36
 */
@SpringBootTest
public class tree {


    @Test
    public void testing(){
        int i = 6;
        int j = 9;
        int k = (i + j) >> 1;
        System.out.println(k);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int i = nums.length / 2;
        int j = nums.length;
        TreeNode root = new TreeNode(nums[i]);
        int[] num1 = new int[i + 1];
        int[] num2 = new int[i + 1];
        for (int k = 0; k < i; k++) {
            num1[k] = nums[k];
        }
        for (int k = 0; k < j - i; k++) {
            num2[k] = nums[k + i + 1];
        }
        root.left = sortedArrayToBST(num1);
        root.right = sortedArrayToBST(num2);
        return  root;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();

            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                levelList.add(node.val);
            }
            res.add(levelList);
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    @Test
    public void test() {

    }
}
