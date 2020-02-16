package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-15
 */

import java.util.Arrays;

/**
 * @program: finance-service
 * @description: leetcode 第767题，重构字符串
 * 如果字符长度为 N 字符串中，某一个字符的数量超过了 (N + 1) / 2 那么不存在解，
 * 否则按照间隔输出字符串就可以得出解
 * @author: ZengShiLin
 * @create: 2020-02-15 下午19:14
 **/
public class _767RefactorString {

    public String reorganizeString(String str) {
        int N = str.length(); //获取字符串长度
        int[] counts = new int[26]; //26个字母
        for (char c : str.toCharArray()) counts[c - 'a'] += 100; //通过 +100 能让相同的字符串就在隔壁（而且可以让重复最多的字符串在末尾）
        for (int i = 0; i < 26; ++i) counts[i] += i; //其余不存在的字符串也给默认值避免统计成相邻字符串
        Arrays.sort(counts); // 排序

        char[] res = new char[N]; //结果字符串
        int t = 1;
        for (int code : counts) { // 这里的code 就是 处理后字符串的编码
            int ct = code / 100; //字符当前出现个数
            char ch = (char) ('a' + (code % 100)); //取得当前字符的ASCII码
            if (ct > (N + 1) / 2) return ""; // 上面的定理
            for (int i = 0; i < ct; ++i) {  //在最多的字符之间穿插插入
                if (t >= N) t = 0;  //如果超出，插入在头部
                res[t] = ch;
                t += 2; //每隔两个位置插入一次
            }
        }
        return String.valueOf(res);
    }


    public static void main(String[] args) {
        _767RefactorString string =new _767RefactorString();
        System.out.println(string.reorganizeString("adafvb"));
    }


}
