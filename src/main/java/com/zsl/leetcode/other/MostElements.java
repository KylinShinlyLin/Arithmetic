package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-21
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @program: finance-service
 * @description: 数组中重复最多的元素
 * @author: ZengShiLin
 * @create: 2020-02-21 下午16:50
 **/
public class MostElements {


    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int temp : nums) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        int result = 0;
        int half = (nums.length / 2) + 1;
        for (Map.Entry<Integer, Integer> temp : map.entrySet()) {
            if (temp.getValue() > result && temp.getValue() >= half) {
                result = temp.getKey();
            }
        }
        return result;
    }


}
