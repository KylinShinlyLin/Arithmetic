package com.zsl.leetcode.Daily;

import java.util.Objects;

/**
 * (先序/中序/后序)遍历判断俩棵树是否相等
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/9 23:24
 */
public class 相同的树_100 {


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

    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            String pRes = this.preOrderTree(p);
            String qRes = this.preOrderTree(q);
            return pRes.equals(qRes);
        }

        private String preOrderTree(TreeNode treeNode) {
            if (Objects.isNull(treeNode)) {
                return "null";
            }
            String res = treeNode.val +
                    this.preOrderTree(treeNode.left) +
                    this.preOrderTree(treeNode.right);
            return res;
        }
    }
}
