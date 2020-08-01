package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-12
 */

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * @program: finance-service
 * @description: LeetCode第1题
 * @author: ZengShiLin
 * @create: 2020-02-12 下午19:52
 **/
public class _1SumTowNum {


    public static void main(String[] args) {
        _1SumTowNum towNum = new _1SumTowNum();
        System.out.println(JSON.toJSONString(towNum.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }


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
        for (int index = 0; index < nums.length; index++) {
            int aim = target - nums[index];
            if (map.containsKey(aim) && map.get(aim) != index) {
                return new int[]{index, map.get(aim)};
            }
        }
        return null;
    }

}
