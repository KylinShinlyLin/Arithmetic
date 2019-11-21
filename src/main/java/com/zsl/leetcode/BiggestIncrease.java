package com.zsl.leetcode;

/**
 * @program: finance-service
 * @description: 我们有一个数字序列包含n个不同的数组，如何求出这个序列汇总最长递增子序列长度，如 2,9,3,6,5,1,7 这样一组数字序列，
 * 最长递增子序列为2,3,5,7。所以最长递增子序列长度为4
 * @author: ZengShiLin
 * @create: 11/21/2019 6:27 PM
 **/
public class BiggestIncrease {


    int counts[];

    /**
     * @param nums 数字
     * @return 最大长度
     */
    private int biggestIncrease(int[] nums) {
        counts = new int[nums.length];
        counts[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            counts[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }
            }
        }
        int res = 0;
        for (int temp : counts) {
            if (temp > res) {
                res = temp;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        BiggestIncrease increase = new BiggestIncrease();
        System.out.println(increase.biggestIncrease(nums));

    }


}
