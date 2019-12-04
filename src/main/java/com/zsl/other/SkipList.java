package com.zsl.other;

/**
 * @program: finance-service
 * @description: 跳跃表的Java实现
 * @author: ZengShiLin
 * @create: 12/3/2019 3:04 PM
 **/
public class SkipList {




    private class Node {

        /**
         * 基础数据
         */
        Integer data;

        /**
         * 向前指针
         */
        Node forWard;

        /**
         * 向后指针
         */
        Node backWard;

        /**
         * 层数
         */
        NodeLevel[] levels;
    }


    private class NodeLevel {

        Node forWard;

        /**
         * 当前层跨度
         */
        int span;
    }



}
