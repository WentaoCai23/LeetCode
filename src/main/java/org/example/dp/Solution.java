package org.example.dp;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

    }

    //找最长递增子序列(非连续)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; i < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //找连续的最长递增子序列
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > result) {
                result = count;
            }
        }
        return result;
    }

}
