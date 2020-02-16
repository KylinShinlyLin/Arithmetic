package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-16
 */

/**
 * @program: finance-service
 * @description: 插入排序
 * @author: ZengShiLin
 * @create: 2020-02-16 下午15:31
 **/
public class InsertSort {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
//
//    public ListNode insertionSortList(ListNode head) {
//        ListNode resHead = new ListNode(0);
//        ListNode node;
//        ListNode pre = null, temp;
//        while (head.next != null) { //遍历 head节点
//            node = head.next;
//            if (resHead.next == null) {
//                resHead.next = node;
//                pre = node;
//            }
//            temp = resHead;
//            while (pre != null && temp.next != null) { //
//                if (temp.next.val >= node.val) { //后一个比当前节点大
//                    node.next = pre.next;
//                    pre.next = node;
//                }
//                temp = temp.next;
//            }
//            head = head.next;
//        }
//        return resHead;
//    }
//
//    public static void main(String[] args) {
//        ListNode head = new ListNode(0);
//        ListNode node1 = new ListNode(4);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(1);
//        ListNode node4 = new ListNode(3);
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        InsertSort sort = new InsertSort();
//        ListNode res = sort.insertionSortList(head);
//        ListNode node;
//        while (res.next != null) {
//            node = res.next;
//            System.out.println(node.val);
//        }
//    }


    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = new ListNode(-1);
        h.next = head;
        ListNode pre = h;
        ListNode cur = head;
        ListNode lat;

        while (cur != null) {
            lat = cur.next; // 记录下一个要插入排序的值
            if (lat != null && lat.val < cur.val) { // 只有 cur.next 比 cur 小才需要向前寻找插入点
                while (pre.next != null && pre.next.val < lat.val) { // 如果当前节点的值小于要插入排序的值
                    pre = pre.next; // 继续向后移动
                }
                ListNode tmp = pre.next;
                pre.next = lat;
                cur.next = lat.next;
                lat.next = tmp;
                pre = h;
            } else {
                cur = lat;
            }
        }

        return h.next;
    }
}
