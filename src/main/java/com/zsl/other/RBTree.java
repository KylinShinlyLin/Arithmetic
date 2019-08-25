package com.zsl.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * @program: arithmetic
 * @description: 红黑树实现
 * 为了方便实现，这里的key都使用Int不写成泛型
 * @author: ZengShiLin
 * @create: 2019-08-10 14:31
 **/
public class RBTree<V> {

    /**
     * 哨兵结点
     */
    private TreeNode<V> sentinel;

    /**
     * 树的构造函数，构造的时候构造哨兵结点
     */
    public RBTree() {
        this.sentinel = new TreeNode<>(null, null, null);
        this.sentinel.parent = this.sentinel;
        this.sentinel.left = this.sentinel;
        this.sentinel.right = this.sentinel;
        this.root = this.sentinel;
    }

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
         * 左节点(默认给与一个哨兵结点)
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
         * 是否红色节点
         */
        private boolean red = false;

        /**
         * @param key      健
         * @param value    值
         * @param sentinel 哨兵
         */
        TreeNode(Integer key, V value, TreeNode<V> sentinel) {
            this.key = key;
            this.value = value;
            this.right = sentinel;
            this.left = sentinel;
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
        return this.balanceInsert(new TreeNode<>(key, value, this.sentinel));
    }

    /**
     * 红黑树插入
     *
     * @param z 需要插入的结点
     * @return 是否插入成功
     */
    private boolean balanceInsert(TreeNode<V> z) {
        TreeNode<V> y = this.sentinel, x = this.root;
        //类似于二叉树的方式应该放入的父亲结点
        while (x != this.sentinel) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        //如果整个树都是空的那新结点就是根结点
        if (y == sentinel) {
            this.root = z;
            //如果是跟结点直接返回成功
            return true;
        } else if (z.key < y.key) {
            //小于父亲就在右边
            y.left = z;
        } else {
            //大于父亲就在左边
            y.right = z;
        }
        z.left = this.sentinel;
        z.right = this.sentinel;
        z.red = true;
        //修复红黑树的平衡性
        return this.insertFixUp(z);
    }

    /**
     * 红黑树修复操作
     * 要处理根节点的情况
     *
     * @param z 需要插入的结点
     * @return 修复结果
     */
    private boolean insertFixUp(TreeNode<V> z) {
        //当插入结点是红色就一直循环
        while (z.parent.red) {
            //插入结点的父亲是左结点
            if (z.parent == z.parent.parent.getLeft()) {
                //去看其右结点
                TreeNode<V> y = z.parent.parent.getRight();
                //如果叔结点满足红色，重新染色(黑色结点下移)
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    //当前子树冲突解决，但是父亲节点冲突可能变化，冲突问题向上转移
                    z = z.parent.parent;
                } else if (z == z.parent.getRight()) { //如果，插入结点位于父亲结点的右边
                    //由于不能变色，向上转移，然后左旋
                    z = z.parent;
                    //左旋
                    this.leftRotate(z);
                }
                z.parent.red = false;
                z.parent.parent.red = true;
                //右旋
                this.rightRotate(z.parent.parent);
            } else {//插入结点的父亲是右结点
                //去看其右结点
                TreeNode<V> y = z.parent.parent.getLeft();
                //重新染色(黑色结点下移)
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    //当前子树冲突解决，但是父亲节点冲突可能变化需要向上转移
                    z = z.parent.parent;
                } else if (z == z.parent.getLeft()) {
                    //由于不能变色，向上转移，然后左旋
                    z = z.parent;
                    //左旋
                    this.rightRotate(z);
                }
                z.parent.red = false;
                z.parent.parent.red = true;
                this.rightRotate(z.parent.parent);
            }
        }
        //无条件对根节点染黑
        this.root.red = false;
        return true;
    }

    /**
     * 左旋
     *
     * @param node 需要旋转的结点
     */
    private void leftRotate(TreeNode<V> node) {
        TreeNode<V> y = node.right;
        node.right = y.left;
        if (y.left != this.sentinel) {
            y.left.parent = node;
        }
        y.parent = node.parent;
        //x就是根结点 所以 y也要变成根结点
        if (node.parent == this.sentinel) {
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
        if (x.right != this.sentinel) {
            x.right.parent = node;
            node.left = x.right;
        }
        //x就是根结点 所以 y也要变成根结点
        if (node.parent == this.sentinel) {
            this.root = x;
        } else if (node == node.parent.left) {
            //x 在左边 y 替换 x 的位置
            node.parent.left = x;
        } else {
            //x 在右边 y 替换 x 的位置
            node.parent.right = x;
        }
        if (x != this.sentinel) {
            //当前结点的左结点替换当前结点(x不为空的情况下)
            x.parent = node.parent;
            //x的右边结点替换为当前被旋转的结点
            x.right = node;
        }
    }

    /**
     * 中序遍历
     */
    public void inOrderTraversal() {
        //递归中序遍历
        //this.inOrderRecursionWalk(this.root); 非递归辅助栈遍历
        this.inOrderWalk(this.root);
        //非递归传统方式遍历
    }

    /**
     * 中序遍历非递归实现（辅助栈）
     *
     * @param root 根节点
     */
    private void inOrderWalk(TreeNode<V> root) {
        TreeNode<V> temp = root;
        //用来实现非递归的栈数据结构（该栈底层使用的是Vector链表结构,避免栈溢出的情况）
        Stack<TreeNode<V>> stack = new Stack<>();
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


    public static void main(String[] args) {
        RBTree<String> tree = new RBTree<>();
        System.out.println(tree.putVal(7, "这个值是7"));
        System.out.println(tree.putVal(3, "这个值是3"));
        System.out.println(tree.putVal(10, "这个值是10"));
        System.out.println(tree.putVal(8, "这个值是8"));
        System.out.println(tree.putVal(18, "这个值是18"));
        System.out.println(tree.putVal(11, "这个值是11"));
        System.out.println(tree.putVal(22, "这个值是22"));
        System.out.println(tree.putVal(15, "这个值是15"));
        System.out.println(tree.putVal(26, "这个值是26"));
        tree.inOrderTraversal();
    }
}
