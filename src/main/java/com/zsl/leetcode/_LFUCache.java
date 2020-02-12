package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-12
 */

import java.util.HashMap;

/**
 * @program: finance-service
 * @description: TODO LUC 缓存实现
 * @author: ZengShiLin
 * @create: 2020-02-12 下午21:08
 **/
public class _LFUCache {

    int capacity;

    //键值对
    private HashMap<Integer, Integer> map;

    //指向Node节点的Map
    private HashMap<Integer, DLinkListNode> nodeMap;

    //双向链表头指针
    private DLinkListNode head;

    //双向链表尾指针
    private DLinkListNode tail;


    public static class DLinkListNode {
        int val;

        DLinkListNode next;

        DLinkListNode pre;

        DLinkListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }


    public _LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        return 0;
    }

    public void put(int key, int value) {

    }
}
