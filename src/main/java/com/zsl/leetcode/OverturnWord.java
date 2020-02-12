package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-11
 */

/**
 * @program: finance-service
 * @description: 单词翻转
 * @author: ZengShiLin
 * @create: 2020-02-11 下午17:45
 **/
public class OverturnWord {

    public static void main(String[] args) {
        OverturnWord word = new OverturnWord();
        // word.reverseWords(new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'});
        word.reverseWords(new char[]{'w', 'o', 'r', 'l', 'd', ' ', 'h', 'e', 'l', 'l', 'o'});
    }


    public void reverseWords(char[] s) {
        int head = s.length - 1;
        int tail = s.length - 1;
        int resIndex = 0;
        char[] res = new char[s.length];
        while (resIndex < (s.length - 1)) {
            if (head == -1) {
                for (int i = head + 1; i <= tail; i++) {
                    res[resIndex] = s[i];
                    resIndex++;
                }
            } else if (s[head] != ' ') {
                head--;
            } else {
                for (int i = head + 1; i <= tail; i++) {
                    res[resIndex] = s[i];
                    resIndex++;
                }
                if (!(++resIndex > s.length - 1)) {
                    res[resIndex] = s[head];
                    tail = --head;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }


}
