package com.zsl.leetcode;

import java.util.HashMap;

/**
 * TODO 算法有问题需要改进
 * Create by ZengShiLin on 2019-05-03
 */
public class Partition_Equal_Subset_Sum_416 {

    /**
     * 用于记忆化搜索
     */
    HashMap<String, Integer> memo = new HashMap();

    /**
     * 用于动态规划
     */
    HashMap<Integer, Boolean> memoD = new HashMap();

    //memo 表示为 位置 [0....i],c 的这些元素，是否可以填充一个大小为C的背包
    private boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //总和可以被平分
        if (sum % 2 != 0) {
            return false;
        }
        return tryPartition(nums, nums.length - 1, sum / 2);
    }


    /**
     * @param nums  输入数字
     * @param index 剩余index位置
     * @param sum   背包剩余大小
     * @return
     */
    private boolean tryPartition(int[] nums, int index, int sum) {
        //刚好填满
        if (0 == sum) {
            return true;
        }

        //没有填满，或者不够使用了
        if (sum < 0 || index < 0) {
            return false;
        }

        //记忆化搜索
        if (null != memo.get("" + index + "" + sum)) {
            return memo.get("" + index + "" + sum) == 1;
        }
        memo.put("" + index + "" + sum, tryPartition(nums, index - 1, sum) ||
                tryPartition(nums, index - 1, sum - nums[index]) ? 1 : 0);

        return memo.get("" + index + "" + sum) == 1;
    }


    public static void main(String[] args) {
        Partition_Equal_Subset_Sum_416 test = new Partition_Equal_Subset_Sum_416();
        System.out.println("动态规划:" + test.canPartitionDynamic(new int[]{1, 5, 11, 5}));
        System.out.println("记忆搜索:" + test.canPartition(new int[]{1, 5, 11, 5}));
    }


    /**
     * TODO
     * ******************【动态规划】******************************************************************
     */

    private boolean canPartitionDynamic(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //总和可以被平分
        if (sum % 2 != 0) {
            return false;
        }

        //下面是具体内容
        int n = nums.length;
        //背包容量
        int C = sum / 2;
        //memo 初始化（针对数组，HashMap可以不必这样）
        for (int i = 0; i <= C; i++)
            memoD.put(i, (nums[0] == i));

        for (int i = 1; i < n; i++)
            for (int j = C; j >= nums[i]; j--) {
                memoD.put(j, memoD.get(j) == null ? false : true
                        || memoD.get(j - nums[i]));
            }
        return memoD.get(C);
    }
}
