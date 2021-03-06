package com.zsl.leetcode.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * [6,3,4,5,2] -> [2,3,4,5,6]
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/9 23:48
 */
public class 恢复二叉搜索树_99 {


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
        public void recoverTree(TreeNode root) {
            List<Integer> nums = new ArrayList<>();
            inorder(root, nums);
            int[] swapped = findTwoSwapped(nums);
            recover(root, 2, swapped[0], swapped[1]);
        }

        public void inorder(TreeNode root, List<Integer> nums) {
            if (root == null) {
                return;
            }
            inorder(root.left, nums);
            nums.add(root.val);
            inorder(root.right, nums);
        }

        /**
         * 查找需要交换的节点
         *
         * @param nums
         * @return
         */
        public int[] findTwoSwapped(List<Integer> nums) {
            int n = nums.size();
            int x = -1, y = -1;
            for (int i = 0; i < n - 1; ++i) {
                if (nums.get(i + 1) < nums.get(i)) {
                    y = nums.get(i + 1);
                    if (x == -1) {
                        x = nums.get(i);
                    } else {
                        break;
                    }
                }
            }
            return new int[]{x, y};
        }

        /**
         * 恢复错误节点
         *
         * @param root
         * @param count
         * @param x
         * @param y
         */
        public void recover(TreeNode root, int count, int x, int y) {
            if (root != null) {
                if (root.val == x || root.val == y) {
                    root.val = root.val == x ? y : x;
                    if (--count == 0) {
                        return;
                    }
                }
                recover(root.right, count, x, y);
                recover(root.left, count, x, y);
            }
        }

    }
}
