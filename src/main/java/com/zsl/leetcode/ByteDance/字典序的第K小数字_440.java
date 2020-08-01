package com.zsl.leetcode.ByteDance;

/**
 * 十叉树先序遍历
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 15:41
 */
public class 字典序的第K小数字_440 {

    class Solution {

        //十叉树先序遍历
        public int findKthNumber(int n, int k) {
            int cur = 1; //字典首字符
            k = k - 1;//扣除掉第一个0节点
            while (k > 0) {
                int num = getNode(n, cur, cur + 1);
                if (num <= k) {//第k个数不在以cur为根节点的树上
                    cur += 1;//cur在字典序数组中从左往右移动
                    k -= num;
                } else {//在子树中
                    cur *= 10;//cur在字典序数组中从上往下移动
                    k -= 1;//刨除根节点
                }
            }
            return cur;
        }

        public int getNode(int n, long first, long last) {
            int num = 0;
            while (first <= n) {
                num += Math.min(n + 1, last) - first;//比如n是195的情况195到100有96个数
                first *= 10;
                last *= 10;
            }
            return num;
        }


    }
}
