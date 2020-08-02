package com.zsl.leetcode.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/2 13:35
 */
public class 二叉树展开为链表_114 {

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

    static class Solution {
        public void flatten(TreeNode root) {
            List<TreeNode> list = new ArrayList<TreeNode>();
            //先前序遍历
            preorderTraversal(root, list);
            int size = list.size();
            //拼接成单链表
            for (int i = 1; i < size; i++) {
                TreeNode prev = list.get(i - 1), curr = list.get(i);
                prev.left = null;
                prev.right = curr;
            }
        }

        /**
         * 前序遍历并保存遍历的节点
         *
         * @param root 根节点
         * @param list 链表
         */
        public void preorderTraversal(TreeNode root, List<TreeNode> list) {
            if (root != null) {
                list.add(root);
                preorderTraversal(root.left, list);
                preorderTraversal(root.right, list);
            }
        }
    }

}
