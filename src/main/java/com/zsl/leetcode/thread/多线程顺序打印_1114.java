package com.zsl.leetcode.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 14:31
 */
public class 多线程顺序打印_1114 {

    class Foo {

        public Foo() {

        }

        //其实也可以使用信号量
        private AtomicInteger firstJobDone = new AtomicInteger(0);
        private AtomicInteger secondJobDone = new AtomicInteger(0);


        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstJobDone.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (firstJobDone.get() != 1) {

            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondJobDone.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (secondJobDone.get() != 1) {

            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            secondJobDone.incrementAndGet();
        }
    }
}
