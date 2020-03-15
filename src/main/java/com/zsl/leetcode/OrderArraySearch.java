package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-21
 */

/**
 * @program: finance-service
 * @description: 有序数组搜索(二分查找)
 * @author: ZengShiLin
 * @create: 2020-02-21 下午17:31
 **/
public class OrderArraySearch {


    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1; // 在[l........r]的范围里面查找target
        while (l <= r) {//区间前闭后闭
            int mid = l + (r - l) / 2; // (l+r)/2 会有溢出的情况
            if (nums[mid] == target) {
                return mid;
            }

            if (target > nums[mid]) {
                l = mid + 1; // target在[mid+1......r]中 需要加1 (因为target已经比较过了)
            } else {
                r = mid - 1; // target在[0.......mid-1]中 需要减1 (因为target已经比较过了)
            }
        }
        return -1;
    }
}
