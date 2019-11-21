package com.zsl.leetcode;

import lombok.Getter;

/**
 * @program: finance-service
 * @description: 我们有一个数字序列包含n个不同的数组，如何求出这个序列汇总最长递增子序列长度，如 2,9,3,6,5,1,7 这样一组数字序列，
 * 最长递增子序列为2,3,5,7。所以最长递增子序列长度为4
 * @author: ZengShiLin
 * @create: 11/21/2019 6:27 PM
 **/
public class BiggestIncrease {

    @Getter
    int total;
    int[] nums;
    int totalTemp;
    boolean flag = true;

    /**
     * @param inputs 数字
     * @return 最大长度
     */
    private void biggestIncrease(int[] inputs) {
        nums = inputs;
        sonMax(nums.length - 1);
    }


    private void sonMax(int index) {
        if (index == 0) {
            total = total + 1;
            return;
        }
        if (nums[index - 1] < nums[index]) {
            total = total + 1;
            sonMax(index - 1);
        } else if (nums[index - 1] >= nums[index] && !flag) {
            flag = false;
            sonMax(index - 1);
        } else if (nums[index - 2] < nums[index - 1] && nums[index - 1] > nums[index] && flag) {
            total = total + 1;
            nums = getSonArray(index - 1, nums);
            totalTemp = total;
            //重置
            total = 0;
            flag = true;
            sonMax(nums.length - 1);
            if (totalTemp > total) {
                total = totalTemp;
            }
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
        int[] nums = {4, 3, 7, 5, 4, 5, 1, 5, 2, 85, 7, 1, 45, 1};
        BiggestIncrease increase = new BiggestIncrease();
        increase.biggestIncrease(nums);
        System.out.println(increase.getTotal());
    }


}
