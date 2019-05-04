package com.zsl.leetcode;

/**
 * Create by ZengShiLin on 2019-05-04
 */
public class Move_Zeroes_283 {


    /**
     * 第一种解法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param nums 需要移动的数组
     */
    public void moveZeroes(int[] nums) {
        //临时数组
        int[] nonZeroElements = new int[nums.length];
        //用来记录位置的标记
        int index = 0;

        //第一次循环把非0 的放到临时数组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroElements[index] = nums[i];
                index++;
            }
        }

        //第二次循环，将临时数组放回去
        for (int i = 0; i < nonZeroElements.length; i++) {
            nums[i] = nonZeroElements[i];
        }

        //第三次循环，将末尾置于0
        for (int i = nonZeroElements.length; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * 第二种解法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int k = 0; // nums中,[0.....k]的元素均为非0元素(存储标记位)
        // 遍历到第i给元素后,保证[0...i]中所有非0元素(遍历标记位)
        // 都按照顺序排列在[0...k]中
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
    }


    /**
     * 第三种解法
     *
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                //如果没有零元素，不需要和自身进行交换
                if (i != k) {
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                    k++;
                } else {
                    k++;
                }
            }
        }
    }


    public static void main(String[] args) {

    }


}