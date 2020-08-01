package com.zsl.leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 15:08
 */
public class 打印零与奇偶数_1116 {

    class ZeroEvenOdd {
        private  Semaphore zero = new Semaphore(1);
        private  Semaphore even = new Semaphore(0);
        private  Semaphore odd = new Semaphore(0);

        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                zero.acquire();
                printNumber.accept(0);
                //判断奇偶(主控在这里)
                if (i % 2 == 1) {
                    odd.release();
                } else {
                    even.release();
                }
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                even.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                odd.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }
    }
}
