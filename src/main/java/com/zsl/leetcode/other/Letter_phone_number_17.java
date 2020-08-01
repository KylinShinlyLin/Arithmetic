package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2019-10-21
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @program: finance-service
 * @description: leetcode 17 题（动态规划）（9宫格手机按键组成）
 * @author: ZengShiLin
 * @create: 2019-10-21 下午23:12
 **/
public class Letter_phone_number_17 {

    private Map<Character, String> letterMap;


    private List<String> res;

    public Letter_phone_number_17() {
        this.letterMap = Maps.newHashMap();
        this.res = Lists.newArrayList();
        this.letterMap.put('0', " ");
        this.letterMap.put('1', "");
        this.letterMap.put('2', "abc");
        this.letterMap.put('3', "def");
        this.letterMap.put('4', "ghi");
        this.letterMap.put('5', "jkl");
        this.letterMap.put('6', "mno");
        this.letterMap.put('7', "pqrs");
        this.letterMap.put('8', "tuv");
        this.letterMap.put('9', "wxyz");
    }


    /**
     * 根据输入的按键，排列组合出所有的可能性
     *
     * @param digits 输入的数字
     * @return 可能性组合
     */
    public List<String> letterCombinations(String digits) {
        res.clear();
        this.findCombination(digits, 0, "");
        return this.res;
    }


    private void findCombination(String digits, int index, String str) {
        if (index == digits.length()) {
            res.add(str);
            return;
        }
        String letters = letterMap.get(digits.charAt(index));
        for (int i = 0; i < letters.length(); i++) {
            this.findCombination(digits, index + 1, str + letters.charAt(i));
        }
    }

    public static void main(String[] args) {
        Letter_phone_number_17 phone_number_17 = new Letter_phone_number_17();
        phone_number_17.letterCombinations("79").forEach(System.out::println);
    }

}
