package com.zsl.leetcode.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/6 21:49
 */
public class 回文对_暴力解法_336 {

    class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) continue;
                    if (match(words[i], words[j])) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(j);
                        ans.add(list);
                    }
                }
            }
            return ans;
        }

        public boolean match(String s1, String s2) {
            int l = 0;
            int r = s2.length() - 1;
            while (l < s1.length() && r >= 0 && s1.charAt(l) == s2.charAt(r)) {
                l++;
                r--;
            }
            if (l < s1.length()) {
                if (r >= 0) return false;
                r = s1.length() - 1;
                while (l < s1.length() && r >= 0 && s1.charAt(l) == s1.charAt(r)) {
                    l++;
                    r--;
                }
            } else {
                l = 0;
                while (l < s2.length() && r >= 0 && s2.charAt(l) == s2.charAt(r)) {
                    l++;
                    r--;
                }
            }
            return l >= r;
        }
    }

}
