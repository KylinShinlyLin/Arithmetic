package com.zsl.other;

/**
 * 二分查找
 * Create by ZengShiLin on 2019-05-03
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch test = new BinarySearch();
    }

    /**
     * 二分查找，非递归
     *
     * @param arr    来源数组
     * @param n      数组长度
     * @param target 需要查找的目标
     * @param <T>    范型对象
     * @return 查找到的位置 index
     */
    private <T extends Comparable> int binarySearch(T arr[], int n, T target) {
        int l = 0, r = n - 1; // 在[l........r]的范围里面查找target
        while (l <= r) {//区间前闭后闭
            int mid = l + (r - l) / 2; // (l+r)/2 会有溢出的情况
            if (arr[mid].equals(target)) {
                return mid;
            }

            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1; // target在[mid+1......r]中 需要加1 (因为target已经比较过了)
            } else {
                r = mid - 1; // target在[0.......mid-1]中 需要减1 (因为target已经比较过了)
            }
        }
        return -1;
    }


}
