package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-16
 */

import java.util.Arrays;

/**
 * @program: finance-service
 * @description: 0-1背包问题，最背包可放多个数
 * @author: ZengShiLin
 * @create: 2020-02-16 下午12:37
 **/
public class Knapsack01 {


    /**
     * @param weight 物品重量数组
     * @param W      背包最大承载量
     * @return
     */
    public int knapsack(int[] weight, int W) {
        if (W <= 0) { //背包没有空间了
            return 0;
        }
        Arrays.sort(weight);
        if (weight[0] > W) return 0; //背包太小
        return maxCount(weight, 0, W);
    }

    /**
     * @param weight 物品数组
     * @param index  物品元素位置
     * @param W      剩余空间
     * @return
     */
    public int maxCount(int[] weight, int index, int W) {
        if (W <= 0) { //背包没有空间了
            return 0;
        }
        if (weight[index] <= W) {
            return Math.max(maxCount(weight, index + 1, W - weight[index])
                    , maxCount(weight, index + 1, W - weight[index]) + 1);
        }
        return 0;
    }

    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();
        System.out.println(knapsack01.knapsack(new int[]{1,2,3}, 6));
    }
}
