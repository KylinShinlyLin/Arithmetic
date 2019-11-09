package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2019-11-03
 */

import java.util.HashMap;

/**
 * @program: finance-service
 * @description: 跳表实现
 * @author: ZengShiLin
 * @create: 2019-11-03 下午13:50
 **/
public class Skiplist_1206 {

    private HashMap<Integer, Integer> map = new HashMap<>();

    public Skiplist_1206() {

    }

    public boolean search(int target) {
        return map.containsKey(target);
    }

    public void add(int num) {
        if (map.containsKey(num)) {
            int count = map.get(num);
            map.put(num, count + 1);
        } else {
            map.put(num, 1);
        }
    }

    public boolean erase(int num) {
        Integer total = map.remove(num);
        if (total == null) {
            return false;
        }
        if (total > 1) {
            map.put(num, total - 1);
        }
        return true;
    }


    public static void main(String[] args) {
        Skiplist_1206 skiplist_1206 = new Skiplist_1206();
        System.out.println("null");
        skiplist_1206.add(1);
        System.out.println("null");
        skiplist_1206.add(2);
        System.out.println("null");
        skiplist_1206.add(3);
        System.out.println(skiplist_1206.search(0));
        System.out.println("null");
        skiplist_1206.add(4);
        System.out.println(skiplist_1206.search(1));
        System.out.println(skiplist_1206.erase(0));
        System.out.println(skiplist_1206.erase(1));
        System.out.println(skiplist_1206.search(1));
    }

}
