package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-21
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: finance-service
 * @description: 找出两数值和等于 target 的所有组合
 * @author: ZengShiLin
 * @create: 2020-02-21 下午17:25
 **/
public class _SumTowNumMulti {


    public int[] twoSum(int[] nums, int target) {
        /**
         * 记忆搜索
         */
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        int num1;
        int num2;
        for (int index = 0; index < nums.length; index++) {
            map.put(nums[index], index);
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> element = new ArrayList<>();
        int remain = target;
        for (int index = 0; index < nums.length; index++) {
            int aim = target - nums[index];
            if (map.containsKey(aim) && map.get(aim) != index) {
                if (remain - nums[index] == 0) {

                }
                return new int[]{index, map.get(aim)};
            }
        }
        return null;
    }
}
