package com.zsl.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by ZengShiLin on 2019-04-21
 * LeetCode 第343题
 * 这里使用的是HashMap 当然也可以使用数组，这样性能会更好
 *
 * @author Shinly
 */
public class Integer_Break_343 {

    Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Integer_Break_343 run = new Integer_Break_343();
        long time = System.nanoTime();
        System.out.println("拆分的最大值是：" + run.solvetion(6));
        System.out.println("执行时间（HashMap创建占用大部分时间）:" + (System.nanoTime() - time) + "纳秒");
    }


    private int solvetion(int n) {
        //初始化缓存大小
        this.memo = new HashMap<>(n);
        return this.breakIntegerDp(n);
    }

    /**
     * 记忆搜索方式
     *
     * @param n
     * @return
     */
    private int breakInteger(Integer n) {
        if (1 == n) {
            return 1;
        }

        Integer res;
        if (null != (res = memo.get(n))) {
            return res;
        }

        //循环的时候需要给res一个默认值
        res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = this.max3(res, i * (n - i), i * breakInteger(n - i));
        }
        memo.put(n, res);
        return res;
    }


    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * 动态规划
     *
     * @return
     */
    private int breakIntegerDp(Integer n) {
        int[] memo = this.initializeArray(n+1, -1);
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }


    /**
     * 初始化数组
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
