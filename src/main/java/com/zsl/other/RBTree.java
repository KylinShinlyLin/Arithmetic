package com.zsl.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: arithmetic
 * @description: 红黑树实现
 * 为了方便实现，这里的key都使用Int不写成泛型
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
         * 是否红色节点（新节点默认都是红色）
         */
        private boolean red = true;

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
     * 放入红黑树
     *
     * @param key   健
     * @param value 值
     * @return 是否插入成功
     */
    public boolean putVal(Integer key, V value) {

        return false;
    }

    /**
     * 红黑树插入
     *
     * @param z 需要插入的结点
     * @return 是否插入成功
     */
    private boolean balanceInsert(TreeNode<V> z) {
        TreeNode<V> y = null, x = this.root;
        //类似于二叉树的方式应该放入的父亲结点
        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        //如果整个树都是空的那新结点就是根结点
        if (y == null) {
            this.root = z;
        } else if (z.key < y.key) {
            //小于父亲就在右边
            y.left = z;
        } else {
            //大于父亲就在左边
            y.right = z;
        }
        z.left = null;
        z.right = null;
        z.red = true;
        //修复红黑树的平衡性
        return this.insertFixUp(z);
    }

    /**
     * 红黑树修复操作
     *
     * @param z 需要插入的结点
     * @return 修复结果
     */
    private boolean insertFixUp(TreeNode<V> z) {
        //当插入结点是红色就一直循环
        while (z.parent.red) {
            //插入结点的父亲本身是左结点
            if (z.parent == z.parent.parent.left) {
                //去看其右结点
                TreeNode<V> y = z.parent.parent.right;
                //重新染色(黑色结点下移)
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                }
                //TODO
            }
            //TODO
        }
        return false;
    }

    /**
     * 左旋
     *
     * @param node 需要旋转的结点
     */
    private void leftRotate(TreeNode<V> node) {
        TreeNode<V> y = node.right;
        node.right = y.left;
        if (y.left != null) {
            y.left.parent = node;
        }
        y.parent = node.parent;
        //x就是根结点 所以 y也要变成根结点
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.left) {
            //x 在左边 y 替换 x 的位置
            node.parent.left = y;
        } else {
            //x 在右边 y 替换 x 的位置
            node.parent.right = y;
        }
        //y比x大所以旋转后x是在y的左边
        y.left = node;
        //x的父亲也变成y
        node.parent = y;
    }


    /**
     * 右旋（相当于左旋的逆向）
     *
     * @param node 需要旋转的结点
     */
    private void rightRotate(TreeNode<V> node) {
        //获取当前结点的左结点为x
        TreeNode<V> x = node.left;
        //当前结点的左结点不为空并且当前结点左结点的右结点不为空
        if (x != null && x.right != null) {
            x.right.parent = node;
            node.left = x.right;
        }
        //x就是根结点 所以 y也要变成根结点
        if (node.parent == null) {
            this.root = x;
        } else if (node == node.parent.left) {
            //x 在左边 y 替换 x 的位置
            node.parent.left = x;
        } else {
            //x 在右边 y 替换 x 的位置
            node.parent.right = x;
        }
        if (x != null) {
            //当前结点的左结点替换当前结点(x不为空的情况下)
            x.parent = node.parent;
            //x的右边结点替换为当前被旋转的结点
            x.right = node;
        }
    }

}
