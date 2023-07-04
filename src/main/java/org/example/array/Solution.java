package org.example.array;

class Solution {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(new Solution().search(nums,9));
    }

    public int search(int[] nums, int target) {

        int n = nums.length;
        int left = 0;
        int right = n-1;
        int mid = 0;

        while (left <= right){ //注意<=
            mid = (left + right) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else if(nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int i = 0; i < length; i++){
            if (nums[i] == val){
                for (int j = i; j < length - 1; j++){
                    nums[j] = nums[j+1];
                }
                length--;
                i--; //很重要！
            }
        }
        return length;
    }
}
