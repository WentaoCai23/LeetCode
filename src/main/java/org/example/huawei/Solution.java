package org.example.huawei;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        new Solution().maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}});
    }

    public int trap(int[] height) {
        int length = height.length;
        if (length <= 2) {
            return 0;
        }
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];
        maxLeft[0] = height[0];
        maxRight[length - 1] = height[length - 1];
        for (int i = 1; i < length; i ++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        for (int i = length - 2; i >= 0; i--) {
            maxRight[i] =  Math.max(height[i], maxRight[i + 1]);
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int tmp = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (tmp > 0) {
                sum += tmp;
            }
        }
        return sum;
    }

    public String compressString(String S) {
        if (S.length() == 0) {
            return S;
        }
        StringBuilder result = new StringBuilder();
        char tmp = S.charAt(0);
        int count = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == tmp) {
                count++;
            } else {
                result.append(tmp);
                result.append(count);
                tmp = S.charAt(i);
                count = 1;
            }
        }
        result.append(tmp);
        result.append(count);
        return result.length() > S.length() ? S : result.toString();
    }

    /*
        首先我们将所有的信封按照 www 值第一关键字升序、hhh 值第二关键字降序进行排序；
        随后我们就可以忽略 www 维度，求出 hhh 维度的最长严格递增子序列，其长度即为答案。
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        //冒泡排序
        for (int j = 0; j < envelopes.length; j++) {
            for (int i = 0; i < envelopes.length - j - 1; i++) {
                if (envelopes[i][0] > envelopes[i + 1][0] || (envelopes[i][0] == envelopes[i + 1][0] && envelopes[i][1] < envelopes[i + 1][1])) {
                    int[] tmp = envelopes[i];
                    envelopes[i] = envelopes[i + 1];
                    envelopes[i + 1] = tmp;
                }
            }
        }
        int result = 1;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}