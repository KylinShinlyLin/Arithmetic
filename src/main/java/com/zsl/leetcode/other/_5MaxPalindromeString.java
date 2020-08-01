package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-15
 */

/**
 * @program: finance-service
 * @description: leetcode 第5题，最长回文串
 * 解法，中心拓展法
 * @author: ZengShiLin
 * @create: 2020-02-15 下午22:37
 **/
public class _5MaxPalindromeString {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int n = s.length();
        int start = 0, end = 0;
        for (int i = 0; i < n - 1; i++) {
            int len1 = centerMaxExpand(s, i, i); //奇数回文串
            int len2 = centerMaxExpand(s, i, i + 1); //偶数回文串
            int len = Math.max(len1, len2);
            if (len > end - start) {  //每一次中心拓展后比较上一个 i 位置的长度
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 中心拓展比较，从 i 点开始往两边拓展，不一样就不是回文串
     */
    private int centerMaxExpand(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1; //返回长度
    }

}
