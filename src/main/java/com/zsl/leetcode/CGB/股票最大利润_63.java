package com.zsl.leetcode.CGB;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 20:06
 */
public class 股票最大利润_63 {

    static class Solution {
        public int maxProfit(int[] prices) {
            int cost = Integer.MAX_VALUE, profit = 0;
            for (int price : prices) {
                cost = Math.min(cost, price);
                profit = Math.max(profit, price - cost);
            }
            return profit;
        }
    }
}
