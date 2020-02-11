package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-11
 */

import java.util.Stack;

/**
 * @program: finance-service
 * @description: 字符计算器 只有 （+ - * /）
 * @author: ZengShiLin
 * @create: 2020-02-11 下午16:10
 **/
public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate("3*3+1"));
    }

    public int calculate(String s) {
        s = s.replace(" ", "");  //字符去空
        Stack<Character> stackOper = new Stack<>();
        Stack<Integer> stackNum = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) { //判断是否为数字
                stackNum.push(Integer.parseInt(String.valueOf(ch)));
            } else if (ch == '*' || ch == '/') {
                i = i + 1;
                int num1 = Integer.parseInt(String.valueOf(stackNum.pop()));
                int num2 = Integer.parseInt(String.valueOf(s.charAt(i)));
                if (ch == '*') {
                    stackNum.push(num1 * num2);
                } else {
                    stackNum.push(num1 / num2);
                }
            } else if (ch == '+' || ch == '-') {
                stackOper.push(ch);
            }
        }
        //开始计算加减
        while (!stackOper.empty()) {
            char oper = stackOper.pop();
            int num1 = stackNum.pop();
            int num2 = stackNum.pop();
            if (oper == '+') {
                stackNum.push(num1 + num2);
            } else {
                stackNum.push(num1 - num2);
            }
        }
        return stackNum.pop();
    }

}
