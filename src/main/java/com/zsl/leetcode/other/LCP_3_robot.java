package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2019-11-21
 */

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 2019-11-21 下午23:34
 **/
public class LCP_3_robot {


    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] single = command.toCharArray();
        int goX = 0, goY = 0;
        //设置陷阱的位置
        Map<String, Integer> map = Arrays.stream(obstacles).collect(Collectors.toMap(e -> e[0] + "_" + e[1], e -> 0));
        for (; ; ) {
            for (char operation : single) {
                if (operation == 'U') {
                    goY++;
                } else {
                    goX++;
                }
                if (goX == x && goY == y) {
                    return true;
                }
                //撞墙
                if (map.containsKey(goY + "_" + goX)) {
                    return false;
                }
                if (goX >= 1e9 || goY >= 1e9) {
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
        LCP_3_robot robot = new LCP_3_robot();
        int[][] a = {{5, 5}, {9, 4}, {9, 7}, {6, 4}, {7, 0}, {9, 5}, {10, 7}, {1, 1}, {7, 5}};
        robot.robot("RRU", a, 1486, 743);
    }
}
