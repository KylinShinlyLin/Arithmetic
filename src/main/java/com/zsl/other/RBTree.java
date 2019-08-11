package com.zsl.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: arithmetic
 * @description: 红黑树实现
 * @author: ZengShiLin
 * @create: 2019-08-10 14:31
 **/
public class RBTree<V> {

    /**
     * 根节点
     */
    private TreeNode<V> root;

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

        /**
         * 是否为红色(新节点都设置成功红色)
         */
        private boolean red;


        TreeNode(Integer key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public String toString() {
            return key + "=" + value;
        }

    }


    /**
     * 插入操作
     *
     * @param key   健
     * @param value 值
     * @return 是否插入成功
     */
    public boolean insert(Integer key, V value) {
        if (null == key || null == value) {
            return false;
        }
        TreeNode<V> node = new TreeNode<>(key, value);
        //新节点都设置成红色
        node.setRed(true);
        //获取根节点
        TreeNode<V> root = this.root;

        TreeNode<V> parent = this.root;

        //找到父亲节点
        while (null != parent) {
            if (node.getKey() < parent.getKey())
                parent = parent.getLeft();
            else
                parent = parent.getLeft();
        }
        //如果根节点为空，本节点就是根节点(先放入再旋转)
        if (null == root) {
            //根节点为黑色
            this.root = root = node;
        } else if (node.getKey() < parent.getKey()) {
            parent.setLeft(node);
            node.setParent(parent);
        } else {
            parent.setRight(node);
            node.setParent(parent);
        }
        return this.rb_insert_fix(root, node);
    }


    /**
     * 红黑树修复
     *
     * @param root 根节点
     * @param x    新增节点
     * @return 是否插入成功
     * <p>
     * x 新增节点
     * xp 新增节点父亲节点
     * xpp 新增节点祖父节点
     * xppl 祖父左节点
     * xppr 祖父右节点
     */
    private boolean rb_insert_fix(TreeNode<V> root, TreeNode<V> x) {
        //一直循环直到全部整棵树染色成功
        for (TreeNode<V> xp, xpp, xppl, xppr; ; ) {
            if ((xp = x.getParent()) == null) {
                //整棵树已经遍历修复完成（直接返回）
                x.red = false;
                return true;
            } else if (!xp.red || (xpp = xp.parent) == null)
                //找到祖父节点
                return true;
            if (xp == (xppl = xpp.left)) {
                //找到祖父右节点，并且判断是否为红色（注意这个位置还不是最后的位置，会依次往下添加黑色节点）
                if ((xppr = xpp.right) != null && xppr.red) {
                    xppr.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.right) {
                        //左旋
                        root = this.rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            //右旋
                            root = this.rotateRight(root, xpp);
                        }
                    }
                }
            } else {
                if (xppl != null && xppl.red) {
                    xppl.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.left) {
                        //右旋
                        root = this.rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            //左旋
                            root = this.rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }


    /**
     * 左旋
     *
     * @param root 根节点
     * @param p    插入的节点
     * @param <V>  范型
     * @return 插入节点结果
     * p 当前节点
     * pp 当前节点的父亲节点
     * r 当前节点的右节点
     * rl 当前节点的右节点的左节点
     */
    private <V> TreeNode<V> rotateLeft(TreeNode<V> root,
                                       TreeNode<V> p) {
        TreeNode<V> r, pp, rl;
        if (p != null && (r = p.right) != null) {
            if ((rl = p.right = r.left) != null)
                rl.parent = p;
            if ((pp = r.parent = p.parent) == null)
                (root = r).red = false;
            else if (pp.left == p)
                pp.left = r;
            else
                pp.right = r;
            r.left = p;
            p.parent = r;
        }
        return root;
    }


    /**
     * 右旋
     *
     * @param root 根节点
     * @param p    插入的节点
     * @param <V>  范型
     * @return 插入节点结果
     */
    private <V> TreeNode<V> rotateRight(TreeNode<V> root,
                                        TreeNode<V> p) {
        TreeNode<V> l, pp, lr;
        if (p != null && (l = p.left) != null) {
            if ((lr = p.left = l.right) != null)
                lr.parent = p;
            if ((pp = l.parent = p.parent) == null)
                (root = l).red = false;
            else if (pp.right == p)
                pp.right = l;
            else
                pp.left = l;
            l.right = p;
            p.parent = l;
        }
        return root;
    }


}
