package com.zsl.test;/**
 * Create by ZengShiLin on 2020-03-15
 */

import org.slf4j.MDC;

/**
 * @program: finance-service
 * @description:
 * @author: ZengShiLin
 * @create: 2020-03-15 下午19:28
 **/
public class MDCtest {


    public static void main(String[] args) throws InterruptedException {
        MDCtest ctest = new MDCtest();
        ctest.test("user", "pd");
    }


    public void test(String username, String password) throws InterruptedException {
        MDC.put("inset", username);
        Thread test = new Thread(() -> MDC.put("inset", "测试测试"));
        test.start();
        Thread.sleep(1000);
    }

}
