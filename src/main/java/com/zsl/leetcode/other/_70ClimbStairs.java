package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-16
 */

/**
 * @program: finance-service
 * @description: leetcode 70题目 爬楼梯（动态规划）
 * @author: ZengShiLin
 * @create: 2020-02-16 下午13:43
 **/
public class _70ClimbStairs {

    public static void main(String[] args) {
        _70ClimbStairs climbStairs = new _70ClimbStairs();
        System.out.println(climbStairs.climbStairs2(44));
    }

    public int climbStairs(int n) {
        if (n <= 0) return 0;
        return climb(1, n) + climb(2, n);
    }


    /**
     * @param step    爬多少台阶
     * @param residue 剩余台阶
     * @return 类型数量
     */
    public int climb(int step, int residue) {
        if (residue <= 0) {
            return 0;
        }
        if (step == residue) {
            return 1;
        } else {
            return climb(1, residue - step)
                    + climb(2, residue - step);
        }
    }


    public int climbStairs2(int n) {
        if (n <= 0) return 0;
        int[] memo = new int[n + 1];
        return climb2(1, n, memo) + climb2(2, n, memo);
    }


    /**
     * @param step  爬多少台阶
     * @param total 总台阶
     * @return 类型数量
     */
    public int climb2(int step, int total, int[] memo) {
        if (step > total) {
            return 0;
        }
        if (step == total) {
            return 1;
        }

        if (memo[step] > 0) {
            return memo[step];
        }
        memo[step] = climb2(step + 1, total, memo) + climb2(step + 2, total, memo);
        return memo[step];
    }


    /**
     * 动态规划
     *
     * @param n 台阶数量
     * @return 总数
     */
    public int climbStairsDynamicPlanning(int n) {
        if (n == 1) { //只有一个阶梯
            return 1;
        }
        int[] dp = new int[n + 1]; //每个数组元素都当成是一个台阶
        dp[1] = 1;  //第一步走一个台阶
        dp[2] = 2;  //第一步走两个台阶
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; //走一步 + 走两步的 台阶总数
        }
        return dp[n];
    }


}
