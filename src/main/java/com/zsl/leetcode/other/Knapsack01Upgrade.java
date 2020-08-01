package com.zsl.leetcode.other;

import java.util.HashMap;

/**
 * 0-1背包问题 背包中最大价值
 * Create by ZengShiLin on 2019-05-03
 */
public class Knapsack01Upgrade {

    /**
     * 这里的记忆
     * K -> 【重量大小 + 价值】
     */
    private HashMap<String, Integer> memo = new HashMap<>();

    /**
     * @param w 每个物品的大小
     * @param v 物品价值
     * @param C 代表背包大小
     * @return
     */
    public int knapsack(int[] w, int[] v, int C) {
        int n = w.length;
        return bestValue(w, v, n - 1, C);
    }

    /**
     * 背包问题记忆化搜索
     * 用 index [0.......index]的物品，填充容积为c的背包的最大价值
     *
     * @param w     物品大小维度数组
     * @param v     物品价值维度数组
     * @param index 第几次放入 +1
     * @param C
     * @return
     */
    private int bestValue(int[] w, int[] v, int index, int C) {
        if (index < 0 || C <= 0) {
            return 0;
        }
        //记忆搜索
        if (null != memo.get("" + index + "" + C)) {
            return memo.get("" + index + "" + C);
        }

        //不放入背包
        int res = bestValue(w, v, index - 1, C);
        if (C >= w[index]) { // 判断背包空间是否还充足
            //不放和放入做比较看哪个大（递归剩余空间大小）
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, C - w[index]));
        }
        //得到的结果放入记忆搜索中
        memo.put("" + index + "" + C, res);
        return res;
    }

    public static void main(String[] args) {
        Knapsack01Upgrade test = new Knapsack01Upgrade();
        System.out.println("价值最大：" + test.knapsack(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5));
    }


    /**
     * *****************************【下面是动态规划】**********************************************************************
     */
    private int bestValueDynamicPlan(int[] w, int[] v, int C) {
        int n = w.length;
        if (0 == n) {
            return 0;
        }
        //先计算，容量为0的情况
        for (int j = 0; j <= C; j++) {
            memo.put("" + 0 + "" + j, j >= w[0] ? v[0] : 0);
        }

        //每一列（容量0 ~到i 的物品）在容量 0 ~ j 的背包中
        //每一行
        for (int i = 1; i < n; i++)
            for (int j = 0; j <= C; j++) {
                //先放入，避免背包放不进去
                memo.put("" + i + "" + j, memo.get("" + (i - 1) + "" + j));
                //背包放的进去的情况
                if (j >= w[i])
                    memo.put("" + i + "" + j, Math.max(memo.get("" + i + "" + j), memo.get("" + (i - 1) + "" + (j - w[i]))));
            }
        return memo.get("" + (n - 1) + "" + C);
    }

}
