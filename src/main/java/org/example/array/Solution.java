package org.example.array;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
//        int[] nums = {-1,0,3,5,9,12};
//        System.out.println(new Solution().search(nums,9));

//        int[] nums = {-7,-3,2,3,11};
//        int[] result = new Solution().sortedSquaresV3(nums);
//        for (int i = 0; i < result.length; i++) {
//            System.out.print(result[i] + " ");
//        }

        System.out.println(new Solution().minSubArrayLenV2(7, new int[]{2, 3, 1, 2, 4, 3}));

    }

    public int search(int[] nums, int target) {

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int mid = 0;

        while (left <= right) { //注意<=
            mid = (left + right) / 2;
            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid - 1;
            else return mid;
        }
        return -1;
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == val) {
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                length--;
                i--; //很重要！
            }
        }
        return length;
    }

    //冒泡排序
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] * nums[j] > nums[j + 1] * nums[j + 1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        return nums;
    }

    public int[] sortedSquaresV2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    //数组其实是有序的， 只不过负数平方之后可能成为最大数了。
    //那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
    //双指针法
    public int[] sortedSquaresV3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int k = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                result[k--] = nums[right] * nums[right];
                right--;
            } else {
                result[k--] = nums[left] * nums[left];
                left++;
            }
        }
        return result;
    }

    //暴力
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int len = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    len = j - i + 1;
                    res = res < len ? res : len;
                    break;
                }
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
    //窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。
    public int minSubArrayLenV2(int target, int[] nums) {
        int sum = 0;
        int len = 0;
        int res = Integer.MAX_VALUE;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                len = j - i + 1;
                res = res < len ? res : len;
                sum -= nums[i];
                i++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int[][] generateMatrix(int n) {
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;

        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;
        }

        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }
}
