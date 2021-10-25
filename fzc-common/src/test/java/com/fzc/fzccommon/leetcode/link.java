package com.fzc.fzccommon.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Stack;

/**
 * @author Flamenco.xxx
 * @date 2021/9/25 14:22
 */
@SpringBootTest
public class link {

    @Test
    public void mergeTwoLists() {
        int i = 1 ;
        int j = 2;
        boolean bool = i > j;
    }



    public boolean isPalindrome(ListNode head) {
        ListNode node1 = head;
        ListNode node2 = null;
        int i = 0;
        int j= 0;
        int fast = 0;
        int slow = 0;
        ListNode x = null;
        ListNode y = null;

        while(head != null && head.val !=head.next.val){
            head = head.next;
            i++;
        }
        i++;
        System.out.println(i);

        while(j< i + 1){
            node2 = node2.next;
            j++;
        }
        node2 = reverseList2(node2);

        while(node2 != null){
            if(node1.val== node2.val){
                node1 = node1.next;
                node2 = node2.next;
            }else{
                return false;
            }
        }
     return true;
    }



    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res;
        if (l1 == null){
            return l2;
        }else if (l2 ==null){
            return l1;
        }
        if(l1.val>l2.val){
            res = l2;
            l2 = l2.next;
        }else{
            res = l1;
            l1 = l1.next;
        }
        ListNode node = res;
        while(l1 !=null ||l2 != null){
            if (l1 ==null){
                node.next = l2;
                l2 = l2.next;
                node = node.next;
                continue;
            }
            if(l2 ==null){
                node.next = l1;
                l1 = l1.next;
                node = node.next;
                continue;
            }
            if(l1.val>l2.val){
                node.next = l2;
                l2 = l2.next;
            }else{
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        return res;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        if (stack.isEmpty()) {
            return null;
        }
        ListNode node = stack.pop();
        ListNode res = node;
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            node.next = temp;
            node = node.next;
        }
        node.next = null;
        return res;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode node = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }


        return node;
    }
}
