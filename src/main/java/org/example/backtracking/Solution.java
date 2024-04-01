package org.example.backtracking;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
//        new Solution().letterCombinations("");
        new Solution().subsets(new int[]{1, 2, 3});
        HashMap<Integer, Integer> map = new HashMap<>();
    }

    Deque<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    public void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            //这里容易出错，要重新new一个path
            res.add(new ArrayList<>(path));
            return;
        }
        //剪枝
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            backtracking(n, k, i + 1);
            // 或者使用path.remove((Integer) i)
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 1, 0);
        return res;
    }

    public void backtracking(int k, int n, int startIndex, int sum) {

        if (path.size() > k) {
            return;
        }

        if (sum == n && path.size() == k) {
            res.add(new ArrayList<>(path));
            sum = 0;
            return;
        }

        for (int i = startIndex; i <= 9; i++) {
            path.addLast(i);
            sum += i;
            backtracking(k, n, i + 1, sum);
            path.removeLast();
            sum -= i;
        }
    }

    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();
    StringBuilder str = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0 || digits == null) {
            return ans;
        }
        backtracking(digits, 0);
        return ans;
    }

    public void backtracking(String digits, int index) {
        if (str.length() == digits.length()) {
            ans.add(new String(str));
            return;
        }

        String letter = letterMap[digits.charAt(index) - '0'];

        for (int i = 0; i < letter.length(); i++) {
            str.append(letter.charAt(i));
            backtracking(digits, index + 1);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, target, 0, 0);
        return res;
    }

    public void backtracking(int[] candidates, int target, int startIndex, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, i, sum);
            path.removeLast();
            sum -= candidates[i];
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0, used);
        return res;
    }

    public void backtracking(int[] candidates, int target, int startIndex, int sum, boolean[] used) {

        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {

            if (candidates[i] + sum > target) {
                break;
            }

            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            sum += candidates[i];
            used[i] = true;
            backtracking(candidates, target, i + 1, sum, used);
            path.removeLast();
            sum -= candidates[i];
            used[i] = false;
        }
    }

//    List<List<String>> res = new ArrayList<>();
//    Deque<String> path = new LinkedList<>();
//
//    public List<List<String>> partition(String s) {
//        backtracking(s, 0);
//        return res;
//    }
//
//    public void backtracking(String s, int startIndex) {
//        if (startIndex >= s.length()) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//
//        for (int i = startIndex; i < s.length(); i++) {
//            String str = s.substring(startIndex, i + 1);
//            if (judge(str)) {
//                path.addLast(str);
//            } else {
//                continue;
//            }
//            backtracking(s, i + 1);
//            path.removeLast();
//        }
//    }
//
//    public boolean judge(String str) {
//        int left = 0;
//        int right = str.length() - 1;
//        while (left < right) {
//            if (str.charAt(left) != str.charAt(right)) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }

    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return result; // 算是剪枝了
        backTrack(s, 0, 0);
        return result;
    }

    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    private void backTrack(String s, int startIndex, int pointNum) {
        if (pointNum == 3) {// 逗点数量为3时，分隔结束
            // 判断第四段⼦字符串是否合法，如果合法就放进result中
            if (isValid(s,startIndex,s.length()-1)) {
                result.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);    //在str的后⾯插⼊⼀个逗点
                pointNum++;
                backTrack(s, i + 2, pointNum);// 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--;// 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);// 回溯删掉逗点
            } else {
                break;
            }
        }
    }

    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
    private Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    public void backtracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.addLast(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }
}
