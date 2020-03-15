package com.zsl.test;/**
 * Create by ZengShiLin on 2020-03-14
 */

import java.util.concurrent.CountDownLatch;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 2020-03-14 下午14:11
 **/
public class VolatileTest {


    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        test.threadAdd();
        System.out.println(VolatileTest.a);
    }

    static volatile int a = 0;

    public void threadAdd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        Thread t1 = new Thread(new AddRunable(countDownLatch));
        Thread t2 = new Thread(new AddRunable(countDownLatch));
        Thread t3 = new Thread(new AddRunable(countDownLatch));
        Thread t4 = new Thread(new AddRunable(countDownLatch));
        Thread t5 = new Thread(new AddRunable(countDownLatch));
        Thread t6 = new Thread(new AddRunable(countDownLatch));
        Thread t7 = new Thread(new AddRunable(countDownLatch));
        Thread t8 = new Thread(new AddRunable(countDownLatch));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        countDownLatch.await();
    }


    class AddRunable implements Runnable {
        CountDownLatch countDownLatch;

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized (VolatileTest.class) {
                    a++;
                }
            }
            countDownLatch.countDown();
        }


        public AddRunable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
    }
}
