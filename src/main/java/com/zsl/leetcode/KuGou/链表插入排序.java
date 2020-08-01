package com.zsl.leetcode.KuGou;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 17:00
 */
public class 链表插入排序 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            ListNode pre = head;
            ListNode cur = head.next;
            dummy.next = head;
            while (cur != null) {
                if (pre.val <= cur.val) {// 本来就有序
                    pre = cur;
                    cur = cur.next;
                } else {
                    ListNode p = dummy;
                    // 找到一个位置使得p < cur < p.next
                    while (p.next.val < cur.val) {
                        p = p.next;
                    }
                    // 将cur插入到p和p.next之间
                    // 因为cur被插到前面，pre的指针需要跳过cur
                    pre.next = cur.next;
                    cur.next = p.next;
                    p.next = cur;
                    // 完成插入后，cur回到pre后面
                    cur = pre.next;
                }
            }
            return dummy.next;
        }

    }

}
