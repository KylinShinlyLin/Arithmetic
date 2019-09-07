package com.zsl.other;

import com.google.common.hash.Hashing;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Create by ZengShiLin on 2019-09-04
 * 哈希一致性算法
 *
 * @author Shinly
 */
public class HashConsistency {


    public static int consistentHash(long input, int buckets) {
        // 检查
        checkArgument(buckets > 0, "buckets must be positive: %s", buckets);
        // 利用内部的LCG算法实现，产生伪随机数
        LinearCongruentialGenerator generator = new LinearCongruentialGenerator(input);
        int candidate = 0;
        int next;

        // Jump from bucket to bucket until we go out of range
        while (true) {
            // generator.nextDouble() 产生伪随机数
            // 每次hash的循环中每一个的next的值总是会固定 :
            // 比如:
            //      hash 1 round 1 -> 9  hash 2 round 1 -> 9
            //      hash 1 round 2 -> 7  hash 2 round 2 -> 7
            //      hash 1 round 3 -> 2  hash 2 round 3 -> 2
            next = (int) ((candidate + 1) / generator.nextDouble());

            if (next >= 0 && next < buckets) {
                //  如果在 0 到 bucket 范围之外, 将这个next值赋值给candidate,重新计算
                candidate = next;
            } else {
                //  如果在 0 到 bucket 范围之内, 就返回这个 candidate 值,作为 input数据存储的槽
                return candidate;
            }
        }
    }

    private static final class LinearCongruentialGenerator {
        private long state;

        public LinearCongruentialGenerator(long seed) {
            this.state = seed;
        }

        public double nextDouble() {
            state = 2862933555777941757L * state + 1;
            return ((double) ((int) (state >>> 33) + 1)) / (0x1.0p31);
        }
    }

    public static void main(String[] args) {
        System.out.println(Hashing.consistentHash(10, 5));
    }
}
