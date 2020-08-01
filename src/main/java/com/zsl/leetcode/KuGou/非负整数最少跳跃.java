package com.zsl.leetcode.KuGou;

/**
 * 最优解，因为可以选择数组内小于其数值的步数
 * 贪心算法
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 17:09
 */
public class 非负整数最少跳跃 {

    static class Solution {
        public int jump(int[] nums) {
            int position = nums.length - 1;
            int steps = 0;
            while (position > 0) {
                for (int i = 0; i < position; i++) {
                    if (i + nums[i] >= position) {
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }
    }

    public static void main(String[] args) {
        非负整数最少跳跃.Solution test = new 非负整数最少跳跃.Solution();
        System.out.println(test.jump(new int[]{2, 3, 1, 1, 4}));
    }
}
