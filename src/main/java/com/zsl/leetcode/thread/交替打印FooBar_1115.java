package com.zsl.leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/1 14:43
 */
public class 交替打印FooBar_1115 {

    class FooBar {

        private final ReentrantLock lock = new ReentrantLock();
        private final Condition fooCondition = lock.newCondition();
        private final Condition barCondition = lock.newCondition();
        private int n;
        private volatile int count = 1;

        public FooBar(int n) {
            this.n = n;
        }


        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                if (count != 1) {
                    fooCondition.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                count = 2;
                barCondition.signal();
                lock.unlock();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                if (count != 2) {
                    fooCondition.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                count = 1;
                fooCondition.signal();
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) {
        交替打印FooBar_1115 test = new 交替打印FooBar_1115();
        Runnable foor = () -> System.out.println("foo");
        Runnable bar = () -> System.out.println("bar");

    }
}
