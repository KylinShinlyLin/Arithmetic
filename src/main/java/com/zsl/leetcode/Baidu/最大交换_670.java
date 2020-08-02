package com.zsl.leetcode.Baidu;


/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/2 21:27
 */
public class 最大交换_670 {

    static class Solution {
        public int maximumSwap(int num) {
            char[] A = Integer.toString(num).toCharArray();
            int[] last = new int[10];
            for (int i = 0; i < A.length; i++) {
                last[A[i] - '0'] = i;
            }

            for (int i = 0; i < A.length; i++) {
                for (int d = 9; d > A[i] - '0'; d--) {
                    if (last[d] > i) {
                        char tmp = A[i];
                        A[i] = A[last[d]];
                        A[last[d]] = tmp;
                        return Integer.parseInt(new String(A));
                    }
                }
            }
            return num;
        }
    }
}
