package com.zsl.leetcode.KuGou;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 酷狗实战
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 16:31
 */
public class 多数元素 {

    static class Solution {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> numMaps = new HashMap<>();
            for (int num : nums) {
                numMaps.put(num, numMaps.getOrDefault(num, 1) + 1);
            }
            Map.Entry<Integer, Integer> entry = numMaps.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
            assert entry != null;
            return entry.getKey();
        }
    }


    public static void main(String[] args) {
        多数元素.Solution test = new 多数元素.Solution();
        System.out.println(test.majorityElement(new int[]{-1, 1, 1, 1, 2, 1}));
    }
}
