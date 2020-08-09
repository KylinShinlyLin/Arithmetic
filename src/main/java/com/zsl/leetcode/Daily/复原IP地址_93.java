package com.zsl.leetcode.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/9 23:07
 */
public class 复原IP地址_93 {

    static class Solution {
        //IP地址分段数量
        static final int SEG_COUNT = 4;
        List<String> ans = new ArrayList<>();
        //用来存储每一段IP的值
        int[] segments = new int[SEG_COUNT];

        public List<String> restoreIpAddresses(String s) {
            segments = new int[SEG_COUNT];
            dfs(s, 0, 0);
            return ans;
        }

        /**
         * dfs 搜索（深度优先）
         *
         * @param s        当前搜索的字符串
         * @param segId    当前第几段IP地址
         * @param segStart 本段开始位置
         */
        public void dfs(String s, int segId, int segStart) {
            // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案（终止条件）
            if (segId == SEG_COUNT) {
                if (segStart == s.length()) {
                    StringBuilder ipAddr = new StringBuilder();
                    for (int i = 0; i < SEG_COUNT; ++i) {
                        ipAddr.append(segments[i]);
                        if (i != SEG_COUNT - 1) {
                            ipAddr.append('.');
                        }
                    }
                    ans.add(ipAddr.toString());
                }
                return;
            }

            // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
            if (segStart == s.length()) {
                return;
            }

            // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
            if (s.charAt(segStart) == '0') {
                segments[segId] = 0;
                dfs(s, segId + 1, segStart + 1);
            }

            // 一般情况，枚举每一种可能性并递归（列举所有，在通过dfs()递归）
            int addr = 0;
            for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
                addr = addr * 10 + (s.charAt(segEnd) - '0');
                if (addr > 0 && addr <= 0xFF) { //255
                    segments[segId] = addr;
                    dfs(s, segId + 1, segEnd + 1);
                } else {
                    break;
                }
            }
        }
    }
}
