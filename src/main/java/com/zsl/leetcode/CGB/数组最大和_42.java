package com.zsl.leetcode.CGB;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 20:09
 */
public class 数组最大和_42 {
    class Solution {
        public int maxSubArray(int[] nums) {
            int res = nums[0];
            for(int i = 1; i < nums.length; i++) {
                //前一个最大和加上自己
                nums[i] += Math.max(nums[i - 1], 0);
                res = Math.max(res, nums[i]);
            }
            return res;
        }
    }
}
