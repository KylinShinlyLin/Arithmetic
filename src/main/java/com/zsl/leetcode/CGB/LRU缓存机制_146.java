package com.zsl.leetcode.CGB;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZengShiLin
 * @version 1.0
 * @date 2020/8/30 20:13
 */
public class LRU缓存机制_146 {

    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

}
