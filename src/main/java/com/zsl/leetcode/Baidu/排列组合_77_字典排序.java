package com.zsl.leetcode.Baidu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/2 21:21
 */
public class 排列组合_77_字典排序 {

    static class Solution {
        public List<List<Integer>> combine(int n, int k) {
            // init first combination
            LinkedList<Integer> nums = new LinkedList<Integer>();
            for (int i = 1; i < k + 1; ++i)
                nums.add(i);
            nums.add(n + 1);

            List<List<Integer>> output = new ArrayList<List<Integer>>();
            int j = 0;
            while (j < k) {
                // add current combination
                output.add(new LinkedList(nums.subList(0, k)));
                // increase first nums[j] by one
                // if nums[j] + 1 != nums[j + 1]
                j = 0;
                while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                    nums.set(j, j++ + 1);
                nums.set(j, nums.get(j) + 1);
            }
            return output;
        }
    }

}
