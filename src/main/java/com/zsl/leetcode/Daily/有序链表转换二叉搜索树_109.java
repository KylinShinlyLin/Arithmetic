package com.zsl.leetcode.Daily;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/18 22:13
 */
public class 有序链表转换二叉搜索树_109 {

    public static class ListNode {
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


    public static class TreeNode {
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

    /**
     * 分治 + 中序遍历优化
     */
    static class Solution {
        ListNode globalHead;

        public TreeNode sortedListToBST(ListNode head) {
            globalHead = head;
            //计算链表长度
            int length = getLength(head);
            //构造平衡树
            return buildTree(0, length - 1);
        }

        /**
         * 计算长度方法
         *
         * @param head ListNode
         * @return 长度
         */
        public int getLength(ListNode head) {
            int ret = 0;
            while (head != null) {
                ++ret;
                head = head.next;
            }
            return ret;
        }

        /**
         * 构建树（分治-递归）
         *
         * @param left  起始位置
         * @param right 中止位置
         * @return TreeNode 节点
         */
        public TreeNode buildTree(int left, int right) {
            //判断指针是否越界
            if (left > right) {
                return null;
            }
            //当前区间的中间位置
            int mid = (left + right + 1) / 2;
            TreeNode root = new TreeNode();
            //设置左边
            root.left = buildTree(left, mid - 1);
            //globalHead 用来走链表
            root.val = globalHead.val;
            globalHead = globalHead.next;
            //设置右边
            root.right = buildTree(mid + 1, right);
            return root;
        }

    }
}
