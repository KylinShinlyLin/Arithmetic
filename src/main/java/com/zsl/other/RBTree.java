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
     * 插入红黑树
     *
     * @param node 需要插入的节点
     * @return 是否插入成功
     */
    public boolean insert(TreeNode<V> node) {

        return false;
    }



}
