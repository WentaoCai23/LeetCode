package org.example.shopee;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        new Solution().reverses("Shopee");
    }

    public ListNode Rotate(ListNode head, int k) {
        // write code here
        int length = 0;
        ListNode p = head;
        ListNode end = null;
        while (p != null) {
            if (p.next == null) {
                end = p;
            }
            length++;
            p = p.next;
        }
        int time = length % k;
        if (time == 0) {
            return head;
        }

        ListNode ptr = head;
        for (int i = 1; i <= length - time - 1; i++) {
            ptr = ptr.next;
        }
        ListNode start = ptr.next;
        ptr.next = null;
        end.next = head;
        return start;
    }

    public String reverses(String original_str) {
        // write code here
        char[] array = original_str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 'a' && array[i] <= 'z') {
                for (int j = i + 1; j < array.length; j++) {
                    if (!(array[j] >= 'a' && array[j] <= 'z')) {
                        reverse(array, i, j - 1);
                        i = j;
                        break;
                    }
                    if (array[j] >= 'a' && array[j] <= 'z' && j == array.length - 1) {
                        reverse(array, i, j);
                        i = j;
                    }
                }
            }
        }
        return new String(array);
    }

    private void reverse(char[] array, int start, int end) {
        if (start == end) {
            return;
        }
        while (start < end) {
            char tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            start++;
            end--;
        }
    }

    List<List<Integer>> result = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();

    public int[] solution(int[] costs, int coins) {
        // write code here
        backtracking(costs, 0);
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (List<Integer> list : result) {
            if (sum(list) > coins) {
                continue;
            } else {
                if (list.size() > max) {
                    max = list.size();
                    res = list;
                }
            }
        }
        int[] ans = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ans[i++] = num;
        }
        return ans;
    }

    private void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}

