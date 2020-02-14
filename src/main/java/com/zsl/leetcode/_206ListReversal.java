package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-14
 */

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 2020-02-14 上午11:03
 **/
public class _206ListReversal {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }


}
