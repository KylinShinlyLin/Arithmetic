package com.zsl.leetcode.other;/**
 * Create by ZengShiLin on 2020-02-12
 */

import java.util.HashMap;

/**
 * @program: finance-service
 * @description: TODO LUC 缓存实现 (O(1)级别)
 * @author: ZengShiLin
 * @create: 2020-02-12 下午21:08
 **/
public class _LRUCache {

    int capacity;

    int total;

    //键值对
    private HashMap<Integer, Integer> cacheMap;

    //指向Node节点的Map
    private HashMap<Integer, DLinkListNode> nodeMap;

    //双向链表头指针
    private DLinkListNode head;

    //双向链表尾指针
    private DLinkListNode tail;


    public static class DLinkListNode {

        Integer key;

        Integer val;

        DLinkListNode next;

        DLinkListNode pre;

        DLinkListNode(Integer key, Integer value) {
            this.key = key;
            this.val = value;
        }
    }

    public static void main(String[] args) {
        _LRUCache cache = new _LRUCache(3);
        cache.put(2, 1);
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
    }


    public _LRUCache(Integer capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        nodeMap = new HashMap<>(capacity);
        head = new DLinkListNode(0, 0);
        tail = new DLinkListNode(0, 0);
    }


    public void put(Integer key, Integer value) {
        if (total >= capacity) {
            //移除最少访问的
            this.removeNode();
        }
        cacheMap.put(key, value);
        DLinkListNode node, temp;
        if ((node = nodeMap.get(key)) != null) { //节点存在
            //头插入
            temp = head.next;
            head.next = node;
            head.next.next = temp;
            temp.pre = head.next;
        } else { //节点不存在
            node = new DLinkListNode(key, value);
            if (head.next == null && tail.next == null) {
                head.next = node;
                tail.next = node;
                nodeMap.put(node.key, node);
                total++;
            } else {
                if (total == 1) {
                    tail.next.next = null;
                }
                //头插入
                temp = head.next;
                head.next = node;
                head.next.next = temp;
                temp.pre = head.next;
                nodeMap.put(node.key, node);
                total++;
            }

        }

    }

    public Integer get(Integer key) {
        Integer res;
        DLinkListNode node, temp;
        if ((res = cacheMap.get(key)) != null) {
            node = nodeMap.get(key);
            temp = head.next;
            head.next = node;
            head.next.next = temp;
            if (node == tail.next && total > 1) {
                tail.next = tail.next.pre;
                tail.next.next = null;
            }
            temp.pre = head.next;
            head.next.pre = null;
            //头插入
            return res;
        }
        return -1;
    }


    /**
     * 移除最后一个节点（最后一个就是访问最少的）
     */
    private void removeNode() {
        DLinkListNode del;
        if ((del = tail.next) != null) {
            cacheMap.remove(del.key);
            nodeMap.remove(del.key);
            tail.next = tail.next.pre;
            tail.next.next = null;
        }
    }

}
