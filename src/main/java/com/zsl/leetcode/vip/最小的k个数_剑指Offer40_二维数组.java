package com.zsl.leetcode.vip;

import java.util.Arrays;

/**
 * 二维转一维再排序查找
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/24 23:08
 */
public class 最小的k个数_剑指Offer40_二维数组 {

    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int rows = matrix.length, columns = matrix[0].length;
            int[] sorted = new int[rows * columns];
            int index = 0;
            for (int[] row : matrix) {
                for (int num : row) {
                    sorted[index++] = num;
                }
            }
            Arrays.sort(sorted);
            return sorted[k - 1];
        }
    }

    public static void main(String[] args) {

    }

}
