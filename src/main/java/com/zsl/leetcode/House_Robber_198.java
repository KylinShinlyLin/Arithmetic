package com.zsl.leetcode;

/**
 * Create by ZengShiLin on 2019-04-21
 * leetCode 第198题
 *
 * @author Shinly
 */
public class House_Robber_198 {
    //记忆化搜索（保存每个分路的计算结果）
    int[] memo;
    public static void main(String[] args) {
        House_Robber_198 run = new House_Robber_198();
        System.out.println("抢劫获得最大收益：" + run.rob(new int[]{2, 7, 9, 3,1}));
    }


    private int rob(int[] houses) {
        //一定要给定初始值
        memo = this.initializeArray(houses.length, -1);
        return this.tryRob(houses, 0);
    }

    //考虑抢劫 houses[index....nums,size()]这个范围里面的房子
    private int tryRob(int[] houses, int index) {
        if (index >= houses.length) {
            return 0;
        }

        //记忆化搜索
        if (memo[index] != -1) {
            return memo[index];
        }
        int res = 0;
        for (int i = index; i < houses.length; i++) {
            res = Math.max(res, houses[i] + tryRob(houses, i + 2));
        }
        memo[index] = res;
        return res;
    }

    /**
     * 初始化数组(初始化通过数组，并且赋值成默认值)
     *
     * @param n     容量大小
     * @param value 值
     * @return 返回数组
     */
    private int[] initializeArray(int n, int value) {
        int[] arrays = new int[n];
        for (int i = 0; i < n; i++) {
            arrays[i] = value;
        }
        return arrays;
    }
}
