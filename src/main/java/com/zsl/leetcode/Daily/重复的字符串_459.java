package com.zsl.leetcode.Daily;

/**
 * 优秀解法
 *
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 21:25
 */
public class 重复的字符串_459 {

    static class Solution {

        public boolean repeatedSubstringPattern(String s) {
            //子需要连续匹配即可
            return (s + s).indexOf(s, 1) != s.length();
        }
    }

}
