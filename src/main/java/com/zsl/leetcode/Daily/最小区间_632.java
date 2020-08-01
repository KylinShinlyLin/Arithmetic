package com.zsl.leetcode.Daily;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 16:06
 */
public class 最小区间_632 {

    static class Solution {
        public int[] smallestRange(List<List<Integer>> nums) {
            int rangeLeft = 0, rangeRight = Integer.MAX_VALUE;
            int minRange = rangeRight - rangeLeft;
            int max = Integer.MIN_VALUE;
            int size = nums.size();
            int[] next = new int[size];
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(index -> nums.get(index).get(next[index])));
            for (int i = 0; i < size; i++) {
                priorityQueue.offer(i);
                max = Math.max(max, nums.get(i).get(0));
            }
            while (true) {
                int minIndex = priorityQueue.poll();
                int curRange = max - nums.get(minIndex).get(next[minIndex]);
                if (curRange < minRange) {
                    minRange = curRange;
                    rangeLeft = nums.get(minIndex).get(next[minIndex]);
                    rangeRight = max;
                }
                next[minIndex]++;
                if (next[minIndex] == nums.get(minIndex).size()) {
                    break;
                }
                priorityQueue.offer(minIndex);
                max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
            }
            return new int[]{rangeLeft, rangeRight};
        }
    }

    public static void main(String[] args) {
        最小区间_632.Solution run = new 最小区间_632.Solution();
        run.smallestRange(Lists.newArrayList(
                Lists.newArrayList(1, 2, 3),
                Lists.newArrayList(2, 4, 5),
                Lists.newArrayList(7, 8, 9)
        ));
    }

}
