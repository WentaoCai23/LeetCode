package org.example.huawei.shixi0410;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        //new Solution().reverseWords(" asdasd df f");
//        Integer[] nums = new Integer[]{3, 2, 4, 1, 6, 7, 9, 8, 5};
//        int[] nums = new int[]{3, 2, 4, 1, 6, 7, 9, 8, 5};
//        new Solution().bubbleSort(num);
        //Arrays.sort(nums, Collections.reverseOrder());
//        Arrays.sort(nums, (o1, o2) -> o2 - o1);
//        System.out.println(Arrays.toString(nums));

//        List<Integer> list = Arrays.asList(2, 1, 4, 3);
//        Collections.reverse(list);
//        Collections.sort(list, Collections.reverseOrder());
//        System.out.println(list);
//        int[] nums = {3, 2, 4, 1, 6, 7, 9, 8, 5};
//        new Solution().quickSort(nums);
//        System.out.println(Arrays.toString(nums));
        //new Solution().shixi041701();
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
        for (int i = 1; i < length; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        for (int i = length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
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

    /*
        首先我们将所有的信封按照 www 值第一关键字升序、hhh 值第二关键字降序进行排序；
        随后我们就可以忽略 www 维度，求出 hhh 维度的最长严格递增子序列，其长度即为答案。
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        //冒泡排序
        for (int j = 0; j < envelopes.length; j++) {
            for (int i = 0; i < envelopes.length - j - 1; i++) {
                if (envelopes[i][0] > envelopes[i + 1][0] || (envelopes[i][0] == envelopes[i + 1][0] && envelopes[i][1] < envelopes[i + 1][1])) {
                    int[] tmp = envelopes[i];
                    envelopes[i] = envelopes[i + 1];
                    envelopes[i + 1] = tmp;
                }
            }
        }
        int result = 1;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //Dijkstra算法
    public int networkDelayTime(int[][] times, int n, int k) {
        // /2避免溢出
        final int INF = Integer.MAX_VALUE / 2;
        //邻接矩阵
        int[][] matrix = new int[n][n];
        //邻接矩阵初始化
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], INF);
        }
        for (int[] time : times) {
            matrix[time[0] - 1][time[1] - 1] = time[2];
        }
        //dist数组
        int[] dist = new int[n];
        //dist数组初始化
        Arrays.fill(dist, INF);
        //源点
        dist[k - 1] = 0;
        //记录顶点是否使用过
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            //找到dist数组中值最小的顶点
            int min = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (min == -1 || dist[j] < dist[min])) {
                    min = j;
                }
            }
            used[min] = true;
            //更新dist数组
            for (int j = 0; j < n; j++) {
                dist[j] = Math.min(dist[j], dist[min] + matrix[min][j]);
            }
        }
        //找到dist数组中的最大值
        int res = Arrays.stream(dist).max().getAsInt();
        return res == INF ? -1 : res;
    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            // 要考虑这种情况" asdasd df f"
            if (i == s.length() - 1) {
                list.add(s.substring(i, i + 1));
                break;
            }
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == ' ') {
                    list.add(s.substring(i, j));
                    i = j;
                    break;
                }
                if (j == s.length() - 1) {
                    list.add(s.substring(i, j + 1));
                    i = j;
                    break;
                }
            }
        }
        Collections.reverse(list);
        String res = "";
        for (String str : list) {
            res += str + " ";
        }
        return res.substring(0, res.length() - 1);
    }

    /*
    第一，为什么要用两层循环把dfs套在里面？
    我们知道很多题目，直接dfs求解就可以返回了，而这题不可以，关键在于这个字符串的起始位置可以不在[0, 0]这个位置。而你如果不用循环套起来，就默认起点只能在[0, 0]，这样会丢掉很多解。
    第二，为什么要用visit？
    由于我们这条路是可以回头的，并非只能往右下方向走，所以可能会遇到回踩前一个刚刚访问过的格子，而这个格子，题目里说是不可以重复使用的。
    第三，为什么visit需要复位？
    因为当前格子作为中途某一处的起始点，并且走不通时，它是可以回退到上一个格子，并且选择其他方向重新开始的。而此时我们不希望当前格子的遍历路径影响到回退后新路径的尝试。
     */
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        // 将每个数字转换成字符串
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 自定义比较规则
        Arrays.sort(strs, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        // 特殊情况处理
        if (strs[0].equals("0")) {
            return "0";
        }
        // 将排序后的字符串连接起来
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int s = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
        return s - overlapArea;
    }

    //冒泡排序
    public void bubbleSort(int[] num) {
        //总共num.length个数，只需要比较num.length - 1次
        for (int i = 0; i < num.length - 1; i++) {
            //每次都从0开始，比较相邻的两个数，如果第一个数大于第二个数，则交换两个数的位置
            //只需要比较num.length - i - 1次，因为前面的数已经排好序了，后面不需要再比较了
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    int tmp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = tmp;
                }
            }
        }
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            //getOrDefault如果能获取到则返回对应的值，否则返回默认值
            int frequency = map.getOrDefault(c, 0) + 1;
            //put添加元素，如果存在会覆盖
            map.put(c, frequency);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        //按照频次降序排列
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        //StringBuffer和StringBuilder的区别是前者为线程安全的，后者为非线程安全的
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        //记录前面一个操作符
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            //如果当前字符是数字时
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            }
            //如果当前字符是操作符或者是最后一个字符时
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || i == s.length() - 1) {
                //根据前面一个操作符进行操作
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    void dfs(char[][] grid, int r, int c) {
        // 判断 base case
        if (!inArea(grid, r, c)) {
            return;
        }
        // 如果这个格子不是岛屿，直接返回
        if (grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '2'; // 将格子标记为「已遍历过」

        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    //快排
    public void quickSort(int[] array) {
        sortSection(array, 0, array.length - 1);
    }

    private void sortSection(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(array, start, end);
        //这里递归对分割点前后的部分快排
        sortSection(array, start, p - 1);
        sortSection(array, p + 1, end);
    }

    private int partition(int[] array, int start, int end) {
        //基准值
        int pivot = array[start];
        //分割点，i <= position之前所有的值比pivot小，i > position之后所有的值比pivot大
        int position = start;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < pivot) {
                position++;
                //交换位置
                int tmp = array[i];
                array[i] = array[position];
                array[position] = tmp;
            }
        }
        //将基准值放到他该待在的位置，并返回该位置
        array[start] = array[position];
        array[position] = pivot;
        return position;
    }

    public void shixi041001() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<String, Integer> logMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = sc.next().split(",");
            String key = split[0] + "," + split[1] + "," + split[2];
            if (!logMap.containsKey(key)) {
                logMap.put(key, Integer.valueOf(split[3]));
            }
        }
        int m = sc.nextInt();
        Map<String, Integer> factorMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String temp = sc.next();
            String[] split = temp.split(",");
            factorMap.put(split[0], Integer.valueOf(split[1]));
        }

        Map<String, Integer> res = new HashMap<>();
        for (String str : logMap.keySet()) {
            String[] split = str.split(",");
            int unitPrice = factorMap.getOrDefault(split[2], 0);
            int time = logMap.get(str);
            if (time < 0 || time > 100) {
                time = 0;
            }
            res.put(split[1], res.getOrDefault(split[1], 0) + unitPrice * time);
        }

        // 可以采用TreeMap，TreeMap会根据key的自然顺序进行排列
        List<String> list = new ArrayList<>();
        for (String s : res.keySet()) {
            list.add(s);
        }
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s + "," + res.get(s));
        }
        sc.close();
    }

    public void shixi041002() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = sc.nextInt();
            }
        }
        father = new int[n + 5];
        sum = new int[n];
        init();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (m[i][j] != 0) {
                    join(i, j, m[i][j]);
                }
            }
        }
        Arrays.sort(sum);
        for (int i = n - 1; i >= 0; i--) {
            if (sum[i] > 0) {
                System.out.print(sum[i] + " ");
            }
        }
        sc.close();
    }

    int[] father;
    int[] sum;

    public void init() {
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
    }

    public int find(int u) {
        if (father[u] == u) {
            return u;
        } else {
            father[u] = find(father[u]);
            return father[u];
        }
    }

    public void join(int u, int v, int val) {
        u = find(u);
        v = find(v);
        sum[u] += val;
        if (u == v) {
            return;
        }
        father[v] = u;
        sum[u] += sum[v];
        sum[v] = 0;
    }

    public boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

    public void shixi041003() {

    }

    public void shixi041004() {
        int[] hash = new int[100001];
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] split = str.split(",");
        for (String s : split) {
            hash[Integer.valueOf(s)]++;
        }
        int flag = split.length / 2 + 1;
        for (int i = 0; i < 100001; i++) {
            if (hash[i] >= flag) {
                System.out.print(i);
                return;
            }
        }
        System.out.print(0);
        sc.close();
    }

    public void shixi041005() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] office = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                office[i][j] = sc.nextInt();
            }
        }
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.nextLine();
        String relations = sc.nextLine();
        String[] split = relations.split(" ");
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < split.length; i++) {
            set.add(Integer.valueOf(split[i]));
        }

        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= office.length || nextY < 0 || nextY >= office[0].length) {
                continue;
            }
            dfs(office, nextX, nextY, visited, set);
        }

        System.out.print(num);

        sc.close();
    }

    int num = 0;
    int[][] directions = {{1, 0}, {0 ,1}, {-1, 0}, {0, -1}};

    public void dfs(int[][] office, int x, int y, boolean[][] visited, Set<Integer> set) {
        if (visited[x][y] || !set.contains(office[x][y])) {
            return;
        }

        visited[x][y] = true;
        num++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + directions[i][0];
            int nextY = y + directions[i][1];
            if (nextX < 0 || nextX >= office.length || nextY < 0 || nextY >= office[0].length) {
                continue;
            }
            dfs(office, nextX, nextY, visited, set);
        }
    }

    public void shixi041006() {
        Scanner sc = new Scanner(System.in);
        int kcal_low = sc.nextInt();
        int kcal_high = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        String[] split = str.split(" ");
        int[] menu = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            menu[i] = Integer.valueOf(split[i]);
        }
        int[] dp = new int[kcal_high + 1];
        dp[0] = 1;

        for (int i = 0; i < menu.length; i++) {
            for (int j = menu[i]; j <= kcal_high; j++) {
                dp[j] += dp[j - menu[i]];
            }
        }

        int result = 0;
        for (int i = kcal_low; i <= kcal_high; i++) {
            result += dp[i];
        }

        System.out.print(result);
        sc.close();
    }
 }
