package com.zsl.other;

import com.alibaba.fastjson.JSON;

/**
 * Create by ZengShiLin on 2019-06-16
 * Java 堆实现
 * 堆的目的是研究优先队列（最小堆的实现）
 */
public class Heap {

    /**
     * 堆数组（堆一般使用数组实现）
     */
    public int[] nums;

    /**
     * 当前堆数据存储的位置
     */
    private int pointer;

    /**
     * 初始化堆的大小
     *
     * @param capacity 初始容量
     */
    public Heap(int capacity) {
        nums = new int[capacity + 1];
        pointer = 1;
    }

    /**
     * 放入堆
     *
     * @param newVal 需要放入的新值
     */
    public void siftUp(int newVal) {
        //判断堆已经初始化
        if (pointer < 0 || nums == null || pointer >= nums.length) return;
        //一般pointer是指向当前位置的下一个位置，先把新值放入这个位置
        nums[pointer] = newVal;
        int i = pointer, p;
        //指向下一个位置
        pointer++;
        //循环遍历，寻找当前新放入值的位置
        while (true) {
            //如果就是第一位无需再找父亲节点
            if (i == 1) break;
            //二叉树的每个子叶都查找自己的父亲节点
            p = i / 2;
            //如果父亲节点比当前节点小，无需再比较找到位置（最小堆的实现）
            if (nums[p] <= nums[i]) break;
            //和父亲交换位置
            this.swap(p, i);
            i = p;
        }
    }

    /**
     * 1. 左子节点如果不存在，break
     * 2. 如果存在右子节点，判断两个子节点的大小，取小的一个
     * 3. 比较nums[i]和最小字节点的大小，小则break，大则替换
     */
    public int siftDown() {
        //堆为空直接返回
        if (pointer < 0 || nums == null || pointer > nums.length) return -1;
        //将第一的元素返回
        int res = nums[1];
        //将末尾元素方第一位（然后下沉下去）
        nums[1] = nums[pointer - 1];
        int i = 1, child;
        pointer--;
        //循环为节点找到的位置
        while (true) {
            child = 2 * i;
            if (child >= pointer) break;
            if (child + 1 < pointer) {
                if (nums[child] > nums[child + 1]) child++;
            }
            if (nums[i] <= nums[child]) break;
            this.swap(i, child);
            i = child;
        }
        return res;
    }


    /**
     * 数组交换
     *
     * @param i
     * @param p
     */
    private void swap(int i, int p) {
        if (i < 0 || p < 0 || nums == null || i >= nums.length || p >= nums.length)
            return;
        int tmp = nums[i];
        nums[i] = nums[p];
        nums[p] = tmp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(16);
        heap.show();
        heap.siftUp(1);
        heap.siftUp(5);
        heap.siftUp(4);
        heap.siftUp(4);
        heap.siftUp(7);
        heap.siftUp(8);
        heap.siftUp(3);
        heap.siftUp(2);
        heap.show();
    }

    /**
     * 大于当前堆的状态
     */
    private void show() {
        System.out.println(JSON.toJSONString(this.nums));
    }
}
