package org.example.meituan;

import java.time.LocalTime;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        //question1();
        //question2();
        question3();
        //question4();
        //question5();
    }

    public static void question1() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] grades = new int[num];
        for (int i = 0; i < num; i++) {
            grades[i] = in.nextInt();
        }
        //System.out.println(num);
        //Arrays.stream(grades).forEach(System.out::println);
        int[] max = new int[num];
        int[] min = new int[num];
        max[0] = grades[0];
        min[0] = grades[0];
        int count = 0;
        for (int i = 1; i < num; i++) {
            if (grades[i] < min[i - 1] || grades[i] > max[i - 1]) {
                count++;
            }
            max[i] = Math.max(grades[i], max[i - 1]);
            min[i] = Math.min(grades[i], min[i - 1]);
        }
        System.out.println(count);
        in.close();
    }

    /**
     * next()在碰到非有效字符（空格、制表符、回车）后就会结束
     * nextLine()在碰到非有效字符（回车）后就会结束
     * next()方法在扫描到非有效字符的时候会将有效字符取走，但会丢下非有效字符在缓冲区中
     * nextLine()方法在扫描的时候会将扫描到的非有效字符一同清理掉
     * 分析：
     * 从区别中可以看到，当使用nextInt或者next的时候，由于是输入enter键作为结束按钮，但是enter键并没有被清理掉，而是在缓存区里，当nextLine运行的时候，会读取到缓存区里的enter键，所以就会不读取数据直接输出
     * 解决：
     * 在使用nextLine()前，加一句sc.nextLine()。或者在每一个 next()、nextDouble() 、nextFloat()、nextInt() 等语句之后加一个nextLine()语句，将被next()去掉的Enter结束符过滤掉
     * 重新new Scanner
     */
    public static void question2() {
        Scanner sc = new Scanner(System.in);
        String pretime = sc.next();
        int n = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine();
        }
//        System.out.println(pretime);
//        System.out.println(n);
//        System.out.println(Arrays.asList(arr));
        LocalTime time = LocalTime.parse(pretime);
        for (int i = 0; i < n; i++) {
            String[] s = arr[i].split(" ");
            //字符串不能用==做判断
            if ("+".equals(s[0])) {
                time = time.plusMinutes(Integer.parseInt(s[1]));
            } else if ("-".equals(s[0])) {
                time = time.minusMinutes(Integer.parseInt(s[1]));
            }
        }
        System.out.println(time);
        sc.close();
    }

    public static void question3() {

    }

    public static void question4() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        a[0] = 1;
        for (int i = 1; i < n; i++) {
            a[i] = Math.min(m, a[i - 1] + 1);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        in.close();
    }

    public static void question5() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Map<Integer, TreeNode> nodes = new HashMap<>();


        for (int i = 1; i <= n; i++) {
            int val = sc.nextInt();
            TreeNode node = new TreeNode(val);
            nodes.put(i, node);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            TreeNode parent = nodes.get(u);
            TreeNode child = nodes.get(v);
            parent.children.add(child);
        }

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int x = sc.nextInt();

            TreeNode targetNode = nodes.get(u);
            int tmp = targetNode.val;
            dfs(targetNode, x);
            targetNode.val = tmp;
        }

        System.out.println(sum(nodes.get(1)));
        sc.close();
    }

    private static void dfs(TreeNode node, int x) {
        if (node == null) {
            return;
        }
        node.val = x;
        for (TreeNode child : node.children) {
            dfs(child, x);
        }
    }

    private static int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int result = 0;
        result += node.val;
        for (TreeNode child : node.children) {
            result += sum(child);
        }
        return result;
    }

}

class TreeNode {
    int val;
    List<TreeNode> children = new ArrayList<>();

    TreeNode(int val) {
        this.val = val;
    }
}
