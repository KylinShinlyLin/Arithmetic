package com.zsl.leetcode;
/**
 * Create by ZengShiLin on 2020-02-14
 */

/**
 * @program: finance-service
 * @description: 链表反转2
 * @author: ZengShiLin
 * @create: 2020-02-14 上午11:28
 **/
public class _92ListReversal {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private boolean stop;
    private ListNode left;

    /**
     * 这里是递归实现
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    private void recurseAndReverse(ListNode right, int m, int n) {
        if (n == 1) {
            return;
        }

        right = right.next;

        if (m > 1) {
            this.left = this.left.next;
        }

        this.recurseAndReverse(right, m - 1, n - 1);

        if (this.left == right || right.next == this.left) { //遍历完成，停止
            this.stop = true;
        }

        if (!this.stop) { //值互换就好，不用换节点
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;
            this.left = this.left.next;
        }
    }


    /**
     * 非递归实现
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        ListNode cur = head, prev = null;
        while (m > 1) {//定位到 cur = m 和 prev = m - 1 的位置
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        /**
         * 当从找到 m 开始，使用third 将后面的链表都反转，但是
         * 反转后就会变成两个断截的链表
         *
         * 通过 con 和 tail 将他们连接上
         */
        ListNode con = prev, tail = cur; //定位到 m 和 m - 1 的位置
        ListNode third;
        while (n > 0) { //找到 n 和 n - 1
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        //将断截的链表接回去
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}
