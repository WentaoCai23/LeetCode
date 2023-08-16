package org.example.string;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("the sky is blue"));
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public String reverseStr(String s, int k) {
        int length = s.length();
        int count = 0;
        char[] charArray = s.toCharArray();
        while (length >= 2 * k) {
            reverseChar(charArray, count * 2 * k, k);
            count++;
            length -= 2 * k;
        }
        if (length >= k) {
            reverseChar(charArray, count * 2 * k, k);
        } else {
            reverseChar(charArray, count * 2 * k, length);
        }
        return String.valueOf(charArray);
    }

    public void reverseChar(char[] s, int start, int length) {
        int left = start;
        int right = start + length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }

    public static String replaceSpaceV2(String s) {
        if (s == null) {
            return null;
        }
        //选用 StringBuilder 单线程使用，比较快，选不选都行
        StringBuilder sb = new StringBuilder();
        //使用 sb 逐个复制 s ，碰到空格则替换，否则直接复制
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i) 为 char 类型，为了比较需要将其转为和 " " 相同的字符串类型
            //if (" ".equals(String.valueOf(s.charAt(i)))){}
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (s.charAt(i) == ' '){
            i++;
        }
        int j = s.length() - 1;
        while (s.charAt(j) == ' '){
            j--;
        }
        for (; i <= j; i++){
            if (i != 0 && s.charAt(i) == ' ' && s.charAt(i-1) == ' '){
                continue;
            }
            stringBuilder.append(s.charAt(i));
        }
        String tmp = new String(stringBuilder);
        String[] split = tmp.split(" ");
        String res = "";
        for (int k = split.length - 1; k >= 0; k--) {
            res += split[k];
            if (k != 0){
                res += " ";
            }
        }
        return res;
    }
}
