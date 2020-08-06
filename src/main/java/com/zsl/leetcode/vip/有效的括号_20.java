package com.zsl.leetcode.vip;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/6 22:32
 */
public class 有效的括号_20 {

    //映射关系
    private HashMap<Character, Character> mappings;

    //以结束符号为健
    public 有效的括号_20() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (this.mappings.containsKey(c)) {
                //为空证明数量不对,#充当占位符
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        有效的括号_20 test = new 有效的括号_20();
        System.out.println(test.isValid("({{}})[]"));
    }
}
