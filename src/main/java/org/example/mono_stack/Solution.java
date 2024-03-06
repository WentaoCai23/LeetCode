package org.example.mono_stack;

public class Solution {

    public static void main(String[] args) {

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

}
