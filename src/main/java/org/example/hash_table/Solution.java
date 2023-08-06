package org.example.hash_table;

import sun.nio.cs.FastCharsetProvider;
import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
//        String s = "anagram";
//        String t = "nagaram";
//        System.out.println(new Solution().isAnagramV2(s, t));

        int[] nums = {2, 2, 2, 2, 2};
        int target = 8;
        System.out.println(new Solution().fourSum(nums, target));
    }

    //暴力
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] flag = new int[t.length()];
        for (int i = 0; i < s.length(); i++) {
            int exist = 0;
            for (int j = 0; j < t.length(); j++) {
                if (flag[j] == 1)
                    continue;
                if (s.charAt(i) == t.charAt(j)) {
                    exist = 1;
                    flag[j] = 1;
                    break;
                }
            }
            if (exist == 0) {
                return false;
            }
        }
        return true;
    }

    //哈希表法
    public boolean isAnagramV2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet1 = new HashSet<>();
        HashSet<Integer> hashSet2 = new HashSet<>();
        for (int i : nums1) {
            hashSet1.add(i);
        }
        for (int i : nums2) {
            if (hashSet1.contains(i)) {
                hashSet2.add(i);
            }
        }
        int[] res = new int[hashSet2.size()];
        int count = 0;
        for (Integer i : hashSet2) {
            res[count] = i;
            count++;
        }
        return res;
    }

    public boolean isHappy(int n) {
        HashSet hashSet = new HashSet();
        while (true) {
            if (sum(n) == 1) {
                return true;
            } else {
                if (hashSet.contains(sum(n))) {
                    return false;
                } else {
                    hashSet.add(sum(n));
                    n = sum(n);
                }
            }
        }
    }

    public int sum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    //暴力
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int i;
        int j;
        for (i = 0; i < nums.length; i++) {
            for (j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public int[] twoSumV2(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = hashMap.get(target - nums[i]);
                return res;
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return res;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (hashMap.containsKey(nums1[i] + nums2[j])) {
                    hashMap.put(nums1[i] + nums2[j], hashMap.get(nums1[i] + nums2[j]) + 1);
                } else {
                    hashMap.put(nums1[i] + nums2[j], 1);
                }
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (hashMap.containsKey(-(nums3[i] + nums4[j]))) {
                    count += hashMap.get(-(nums3[i] + nums4[j]));
                }
            }
        }
        return count;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            count1[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            count2[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < count1.length; i++) {
            if (count2[i] < count1[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean canConstructV2(String ransomNote, String magazine) {
        // shortcut
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for (char c : magazine.toCharArray()) {
            record[c - 'a'] += 1;
        }

        for (char c : ransomNote.toCharArray()) {
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for (int i : record) {
            if (i < 0) {
                return false;
            }
        }

        return true;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (nums[i] > 0) {
                return lists;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(nums[i]);
                    arrayList.add(nums[left]);
                    arrayList.add(nums[right]);
                    lists.add(arrayList);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int j = 0; j < nums.length - 3; j++) {
            if (nums[j] > 0 && nums[j] > target) {
                return lists;
            }
            if (j > 0 && nums[j] == nums[j - 1]) {
                continue;
            }
            for (int i = j + 1; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                if (i > j + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                while (left < right) {
                    if (nums[j] + nums[i] + nums[left] + nums[right] > target) {
                        right--;
                    } else if (nums[j] + nums[i] + nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        lists.add(Arrays.asList(nums[j], nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return lists;
    }
}
