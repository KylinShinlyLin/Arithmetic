package com.zsl.leetcode;

/**
 * @program: finance-service
 * @description: 我们有一个数字序列包含n个不同的数组，如何求出这个序列汇总最长递增子序列长度，如 2,9,3,6,5,1,7 这样一组数字序列，
 * 最长递增子序列为2,3,5,7。所以最长递增子序列长度为4
 * @author: ZengShiLin
 * @create: 11/21/2019 6:27 PM
 **/
public class BiggestIncrease {


    int total;
    int[] nums;
    boolean partition = false;

    /**
     * @param inputs 数字
     * @return 最大长度
     */
    private int biggestIncrease(int[] inputs) {
        nums = inputs;
        return sonMax(nums.length - 1);
    }


    private int sonMax(int index) {
        if (index == 0 && !partition) {
            return 1;
        } else if (index == 0 && partition) {
            return 0;
        }
        if (nums[index - 1] < nums[index]) {
            return total + 1 + sonMax(index - 1);
        } else if (nums[index - 2] < nums[index - 1] && nums[index - 1] > nums[index]) {
            nums = getSonArray(index - 1, nums);
            partition = true;
            return sonMax(nums.length - 1) ;
        } else {
            return total + sonMax(index - 1);
        }
    }


    private int[] getSonArray(int index, int[] nums) {
        int[] son = new int[index + 1];
        for (int temp = index, i = index; i >= 0; i--, temp--) {
            son[temp] = nums[i];
        }
        return son;
    }

    public static void main(String[] args) {
        int[] nums = {11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        BiggestIncrease increase = new BiggestIncrease();
        System.out.println(increase.biggestIncrease(nums));

    }


}
