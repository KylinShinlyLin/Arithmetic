package com.zsl.leetcode.Daily;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/3 22:09
 */
public class 字符串相加_415 {

    static class Solution {
        public String addStrings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, add = 0;
            StringBuffer ans = new StringBuffer();
            while (i >= 0 || j >= 0 || add != 0) {
                //和通过字符0判断大小
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int result = x + y + add;
                //取余数
                ans.append(result % 10);
                //计算进位
                add = result / 10;
                i--;
                j--;
            }
            // 计算完以后的答案需要翻转过来
            ans.reverse();
            return ans.toString();
        }
    }
}
