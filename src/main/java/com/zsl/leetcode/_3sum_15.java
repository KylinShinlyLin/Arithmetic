package com.zsl.leetcode;

import java.util.*;

/**
 * Create by ZengShiLin on 2019-05-25
 */
public class _3sum_15 {

    /**
     * 用于记忆搜索
     */
    Map<Integer, Integer> memo = new HashMap();

    /**
     * 通过记忆搜索和指针对撞的方式
     * 空间复杂度忽略排序（O(n)）
     * 时间复杂度忽略排序（O(n^2)）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //先排序
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> temp;
        for (int right = nums.length - 1; right >= 2; right--) {
            int left = 0;
            int indexRight = right - 1;
            int target = 0 - nums[right];
            if (memo.get(target) != null && memo.get(target) > indexRight) {
                //如果计算过的就不再计算
            } else {
                memo.put(target, indexRight);
                while (left < indexRight) {
                    //相减等于目标
                    if (nums[left] + nums[indexRight] == target) {
                        temp = new ArrayList<>();
                        temp.add(nums[left]);
                        temp.add(nums[indexRight]);
                        temp.add(nums[right]);
                        res.add(temp);
                        indexRight--;
                    } else if (nums[left] + nums[indexRight] < target) {
                        left++;
                    } else {
                        indexRight--;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }


    public static void main(String[] args) {
        _3sum_15 test = new _3sum_15();
        //test.threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach(e -> System.out.println(e.toString()));
        //test.threeSum(new int[]{1, 2, -2, -1}).forEach(e -> System.out.println(e.toString()));
        //test.threeSum(new int[]{3, 0, -2, -1, 1, 2}).forEach(e -> System.out.println(e.toString()));
        test.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}).forEach(e -> System.out.println(e.toString()));
    }
}
