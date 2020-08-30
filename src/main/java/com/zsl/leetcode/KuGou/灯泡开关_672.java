package com.zsl.leetcode.KuGou;

import java.util.HashSet;
import java.util.Set;

/**
 * 只用考虑6个灯泡
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 20:48
 */
public class 灯泡开关_672 {


    class Solution {

        /**
         * @param n 灯泡数量（默认为开）
         * @param m 操作次数
         * @return
         */
        public int flipLights(int n, int m) {
            Set<Integer> seen = new HashSet();
            n = Math.min(n, 6);
            int shift = Math.max(0, 6 - n);
            //这里对于16种操作
            for (int cand = 0; cand < 16; ++cand) {
                int bcount = Integer.bitCount(cand);
                if (bcount % 2 == m % 2 && bcount <= m) {
                    int lights = 0;
                    if (((cand) & 1) > 0) lights ^= 0b111111 >> shift;
                    if (((cand >> 1) & 1) > 0) lights ^= 0b010101 >> shift;
                    if (((cand >> 2) & 1) > 0) lights ^= 0b101010 >> shift;
                    if (((cand >> 3) & 1) > 0) lights ^= 0b100100 >> shift;
                    seen.add(lights);
                }
            }
            return seen.size();
        }
    }

}
