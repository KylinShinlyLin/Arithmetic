package com.zsl.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * 二叉树的实现
 * Create by ZengShiLin on 2019-07-28
 */
public class BinaryTree<V> {

    /**
     * 根节点
     */
    private TreeNode<V> root;

    /**
     * 放置值
     *
     * @param key   健
     * @param value 值
     * @return 根节点
     */
    public TreeNode putVal(Integer key, V value) {
        TreeNode<V> t = this.root;
        TreeNode<V> x = new TreeNode<>(key, value);
        TreeNode<V> p = null;
        //循环找到当前的parent
        while (t != null) {
            p = t;
            if (x.getKey() < t.getKey())
                t = t.getLeft();
            else
                t = t.getRight();
        }
        x.setParent(p);
        //和parent作比较,插入到指定的位置
        if (null == p) {
            //如果当前根节点为空回填根节点
            this.root = x;
            return x;
        } else if (x.getKey() < p.getKey())
            p.setLeft(x);
        else
            p.setRight(x);
        return this.root;
    }


    /**
     * 中序遍历
     */
    public void inOrderTraversal() {
        //递归中序遍历
        //this.inOrderRecursionWalk(this.root);
        //非递归辅助栈遍历
        this.inOrderWalk(this.root);
        //非递归传统方式遍历
    }

    /**
     * 前序遍历
     */
    public void preOrderTraversal() {
        this.preOrderRecursionWalk(this.root);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraversal() {
        this.postOrderRecursionWalk(this.root);
    }

    /**
     * 中序遍历递归实现
     *
     * @param node 节点
     */
    private void inOrderRecursionWalk(TreeNode node) {
        if (null == node) {
            return;
        }
        inOrderRecursionWalk(node.left);
        System.out.println(node.key + "," + node.value);
        inOrderRecursionWalk(node.right);
    }

    /**
     * 中序遍历非递归实现（辅助栈）
     *
     * @param root 根节点
     */
    private void inOrderWalk(TreeNode root) {
        TreeNode temp = root;
        //用来实现非递归的栈数据结构（该栈底层使用的是Vector链表结构,避免栈溢出的情况）
        Stack<TreeNode> stack = new Stack<>();
        //遍历左边
        while (null != temp || !stack.isEmpty()) {
            while (null != temp) {
                stack.push(temp);
                temp = temp.getLeft();
            }
            if (!stack.isEmpty()) {
                temp = stack.pop();
                if (null != temp) {
                    System.out.println(temp.key + "," + temp.value);
                    //转向
                    temp = temp.getRight();
                }
            }
        }
    }

    /**
     * 中序遍历非递归非辅助栈（传统方式实现）
     *
     * @param root 根节点
     */
    private void inOrderWalkTradition(TreeNode root) {
        TreeNode p = root;
        while (null != p) {
            TreeNode left = p.getLeft();
            if (null != left) {
                while (null != left.getLeft() && left.getLeft() != root) // 找到以 p 为根节点树的最右孩子
                    left = left.getRight();
                if (null == left.getRight()) {

                }
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param node 节点
     */
    private void preOrderRecursionWalk(TreeNode node) {
        if (null == node) {
            return;
        }
        System.out.println(node.key + "," + node.value);
        preOrderRecursionWalk(node.left);
        preOrderRecursionWalk(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node 节点
     */
    private void postOrderRecursionWalk(TreeNode node) {
        if (null == node) {
            return;
        }
        postOrderRecursionWalk(node.left);
        postOrderRecursionWalk(node.right);
        System.out.println(node.key + "," + node.value);
    }


    /**
     * 搜索(类比 二分查找)
     *
     * @param key 根据key搜索节点
     * @return 找到的节点
     */
    public TreeNode findNode(Integer key) {
        TreeNode temp = this.root;
        while (null != temp) {
            if (key.equals(temp.getKey()))
                return temp;
            if (key < temp.getKey())
                temp = temp.getLeft();
            else
                temp = temp.getRight();
        }
        return null;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class TreeNode<V> {

        /**
         * 健
         */
        private Integer key;

        /**
         * 值
         */
        private V value;

        /**
         * 左节点
         */
        private TreeNode<V> left;

        /**
         * 右节点
         */
        private TreeNode<V> right;

        /**
         * 父亲节点
         */
        private TreeNode<V> parent;


        TreeNode(Integer key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.putVal(3, "这个是3");
        tree.putVal(4, "这个是4");
        tree.putVal(1, "这个是1");
        tree.putVal(5, "这个是5");
        tree.putVal(2, "这个是2");
        //中序遍历
        tree.inOrderTraversal();
        // 先序遍历
        // tree.preOrderTraversal();
        // 后序遍历
        // tree.postOrderTraversal();
        System.out.println("查询到的结果:" + tree.findNode(10));
    }

}
