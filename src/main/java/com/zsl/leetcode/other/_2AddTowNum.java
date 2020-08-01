package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-12
 */

/**
 * @program: finance-service
 * @description: leetCode第2题
 * @author: ZengShiLin
 * @create: 2020-02-12 下午19:51
 **/
public class _2AddTowNum {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        _2AddTowNum towNum = new _2AddTowNum();
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;
        ListNode head = towNum.addTwoNumbers(a1, b1);
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
        }
    }


    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode head = new ListNode(0); //伪造头部
        ListNode p = a, q = b, curr = head;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //如果加完了最高位，最后还要进位
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }


}
