package com.zsl.leetcode.Baidu;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/2 20:59
 */
public class 排列组合_77_回溯 {


    static class Solution {

        List<List<Integer>> output = new LinkedList();
        int n;
        int k;

        public void backtrack(int first, LinkedList<Integer> curr) {
            // if the combination is done
            if (curr.size() == k)
                output.add(new LinkedList(curr));

            for (int i = first; i < n + 1; ++i) {
                // add i into the current combination
                curr.add(i);
                // use next integers to complete the combination
                backtrack(i + 1, curr);
                // backtrack
                curr.removeLast();
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            this.n = n;
            this.k = k;
            backtrack(1, new LinkedList<Integer>());
            return output;
        }

    }
}
