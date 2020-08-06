package com.zsl.leetcode.vip;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/6 22:58
 */
public class 最后一个单词的长度_58 {
    class Solution {
        public int lengthOfLastWord(String s) {
            int end = s.length() - 1;
            while (end >= 0 && s.charAt(end) == ' ') end--;
            if (end < 0) return 0;
            int start = end;
            while (start >= 0 && s.charAt(start) != ' ') start--;
            return end - start;
        }
    }

}
