package com.zsl.leetcode.Daily;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 21:20
 */
public class 反转字符串中的单词_557 {

    static class Solution {
        public String reverseWords(String s) {
            StringBuffer ret = new StringBuffer();
            int length = s.length();
            int i = 0;
            while (i < length) {
                //记录当前单词的起始位置
                int start = i;
                while (i < length && s.charAt(i) != ' ') {
                    i++;
                }
                //单词记录并且反转
                for (int p = start; p < i; p++) {
                    ret.append(s.charAt(start + i - 1 - p));
                }
                while (i < length && s.charAt(i) == ' ') {
                    i++;
                    ret.append(' ');
                }
            }
            return ret.toString();
        }
    }

}
