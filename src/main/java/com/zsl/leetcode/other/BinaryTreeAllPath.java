package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-21
 */

import java.util.LinkedList;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 2020-02-21 下午17:40
 **/
public class BinaryTreeAllPath {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxPathSum(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        if (root == null)
            return 0;

        LinkedList<TreeNode> node_stack = new LinkedList<>();
        LinkedList<String> path_stack = new LinkedList<>();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        int res = 0;
        while (!node_stack.isEmpty()) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null)) {
                paths.add(path);
                res++;

            }
            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + node.left.val);
                res = res + 2;
            }
            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + node.right.val);
                res = res + 2;
            }
        }
        return res;
    }
}
