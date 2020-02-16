package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-15
 */

/**
 * @program: finance-service
 * @description: leetcode 53题，最大子序和
 * @author: ZengShiLin
 * @create: 2020-02-15 下午22:48
 **/
public class _53MaxSum {

    public int maxSubArray(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for(int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }



}
