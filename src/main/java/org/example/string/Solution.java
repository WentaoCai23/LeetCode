package org.example.string;

public class Solution {

    public static void main(String[] args) {
        //System.out.println(new Solution().reverseWords("the sky is blue"));
        System.out.println(new Solution().repeatedSubstringPattern("aba"));
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
        return s.replaceAll(" ", "%20");
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
        while (s.charAt(i) == ' ') {
            i++;
        }
        int j = s.length() - 1;
        while (s.charAt(j) == ' ') {
            j--;
        }
        for (; i <= j; i++) {
            if (i != 0 && s.charAt(i) == ' ' && s.charAt(i - 1) == ' ') {
                continue;
            }
            stringBuilder.append(s.charAt(i));
        }
        String tmp = new String(stringBuilder);
        String[] split = tmp.split(" ");
        String res = "";
        for (int k = split.length - 1; k >= 0; k--) {
            res += split[k];
            if (k != 0) {
                res += " ";
            }
        }
        return res;
    }

    public String reverseLeftWords(String s, int n) {
        char[] charArray = s.toCharArray();
        reverseChar(charArray, 0, s.length());
        reverseChar(charArray, 0, s.length() - n);
        reverseChar(charArray, s.length() - n, n);
        return String.valueOf(charArray);
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        //求next数组
        getNext(next, needle);

        int j = 0;
        //i不会回头，会一直往前移动，j会回头
        for (int i = 0; i < haystack.length(); i++) {
            //当不等时，j跟据next数组跳转
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            //当相等时，j往前移动
            if (needle.charAt(j) == haystack.charAt(i))
                j++;
            //匹配成功
            if (j == needle.length())
                return i - needle.length() + 1;
        }
        return -1;

    }

    //其实相当于模式串对自身又进行了kmp
    private void getNext(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }

    //在判断 s + s 拼接的字符串里是否出现一个s的的时候，要刨除 s + s 的首字符和尾字符，这样避免在s+s中搜索出原来的s，我们要搜索的是中间拼接出来的s
    //我们可以从位置1开始查询，并希望查询结果不为位置n，这与移除字符串的第一个和最后一个字符是等价的
    //index(s, 1)相当于去头
    //当没有去尾的情况下，如果s+s里不存在一个s时，(s + s).indexOf(s, 1) 必然等于 s.length()
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
}
