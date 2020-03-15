package com.zsl.leetcode;/**
 * Create by ZengShiLin on 2020-02-21
 */

import java.util.Arrays;

/**
 * @program: finance-service
 * @description: 求三个数相加最接近  target 的三个数
 * @author: ZengShiLin
 * @create: 2020-02-21 下午17:16
 **/
public class _16ThreeSumClosest {


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); //先对数组排序
        int ans = nums[0] + nums[1] + nums[2]; //计算前三个数值和（从小大大里路上应该是最小的，但是有可能是负数）
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans)) //一次次比较绝对值
                    ans = sum;
                if (sum > target)  //太大了，结尾指针回退
                    end--;
                else if (sum < target) //太小了开始指针推进
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }


}
