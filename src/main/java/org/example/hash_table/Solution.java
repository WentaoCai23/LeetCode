package org.example.hash_table;

import sun.nio.cs.FastCharsetProvider;
import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(new Solution().isAnagramV2(s, t));
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

}
