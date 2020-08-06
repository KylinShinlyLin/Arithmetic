package com.zsl.leetcode.vip;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/6 23:04
 */
public class 链表求和 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //初始进位为0
            int pre = 0;
            //操作数
            ListNode mid = new ListNode(0);
            //返回头节点
            ListNode anws = mid;
            //当l1和l2都不为null时进入while循环
            while (l1 != null && l2 != null) {
                //进位余数
                mid.val = (l1.val + l2.val + pre) % 10;
                //更新进位
                pre = (l1.val + l2.val + pre) / 10;
                //链表下一个节点
                l1 = l1.next;
                l2 = l2.next;
                //头节点更新后判断是否为空
                if (l1 == null) {
                    //如果l1头节点为空且进位为0，则操作数的next直接为l2剩下的
                    if (pre == 0) {
                        mid.next = l2;
                        return anws;
                    } else {
                        //如果有进位，则递归调用addTwoNumbers方法
                        mid.next = addTwoNumbers(l2, new ListNode(pre));
                        return anws;
                    }
                }
                //同上
                if (l2 == null) {
                    if (pre == 0) {
                        mid.next = l1;
                        return anws;
                    } else {
                        mid.next = addTwoNumbers(l1, new ListNode(pre));
                        return anws;
                    }
                }
                //l1 l2更新后都不为null，则设置操作数为0 进入下一次while循环
                mid.next = new ListNode(0);
                mid = mid.next;
            }
            //l1为null，直接不能进入上面while循环的情况下，直接返回l2
            if (l1 == null) {
                return l2;
            }//同上
            else {
                return l1;
            }
        }
    }

}
