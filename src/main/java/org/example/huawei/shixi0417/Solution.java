package org.example.huawei.shixi0417;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
//        new Solution().shixi041702();
//        new Solution().dijkstra(new int[][]{{0, 6, 3, INF, INF, INF}, {INF, 0, 2, 5, INF, INF}, {INF, 2, 0, 3, 4, INF}, {INF, INF, INF, 0, 2, 3}, {INF, INF, INF, 2, 0, 5}, {INF, INF, INF, INF, INF, 0}}, 0);
        new Solution().shixi041703();
    }

    public void shixi041701() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque<String> deque = new LinkedList<>();
        String secondTop = "";
        for (int i = 0; i < n; i++) {
            String temp = sc.next();
            if (deque.size() == 0) {
                deque.push(temp);
            } else if (deque.size() == 1) {
                secondTop = deque.peek();
                deque.push(temp);
            } else {
                if (secondTop.equals(temp) && deque.peek().equals(temp)) {
                    deque.pop();
                    deque.pop();
                } else {
                    secondTop = deque.peek();
                    deque.push(temp);
                }
            }
        }

        if (deque.isEmpty()) {
            System.out.print(0);
        } else {
            while (!deque.isEmpty()) {
                if (deque.size() == 1) {
                    System.out.print(deque.removeLast());
                } else {
                    System.out.print(deque.removeLast() + " ");
                }
            }
        }
        sc.close();
    }

    public void shixi041702() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        // 记录所有的根节点
        Set<String> rootSet = new HashSet<>();
        // 记录每个节点的子节点
        Map<String, Set<String>> nodeMap = new HashMap<>();
        // 记录每个节点对应问题的个数
        Map<String, int[]> questionMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String son = sc.next();
            String father = sc.next();
            int questionType = sc.nextInt();
            int num = sc.nextInt();

            if ("*".equals(father)) {
                if (!rootSet.contains(son)) {
                    rootSet.add(son);
                }
            } else {
                if (!nodeMap.containsKey(father)) {
                    Set<String> temp = new HashSet<>();
                    temp.add(son);
                    nodeMap.put(father, temp);
                } else {
                    Set<String> temp = nodeMap.get(father);
                    temp.add(son);
                    nodeMap.put(father, temp);
                }
            }
            if (!nodeMap.containsKey(son)) {
                nodeMap.put(son, new HashSet<>());
            }
            if (!questionMap.containsKey(son)) {
                int[] temp = new int[2];
                temp[questionType] = num;
                questionMap.put(son, temp);
            } else {
                int[] temp = questionMap.get(son);
                temp[questionType] = num;
                questionMap.put(son, temp);
            }
        }

        int count = 0;

        for (String root : rootSet) {
            int di = dfs(root, nodeMap, questionMap);
            if (di > m) {
                count++;
            }
        }
        System.out.print(count);
    }

    public int dfs(String root, Map<String, Set<String>> nodeMap, Map<String, int[]> questionMap) {
        int di = 5 * questionMap.get(root)[0] + 2 * questionMap.get(root)[1];
        Set<String> sonSet = nodeMap.get(root);
        for (String son : sonSet) {
            di += dfs(son, nodeMap, questionMap);
        }
        return di;
    }

    public void shixi041703() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] delayMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                delayMatrix[i][j] = sc.nextInt();
            }
        }
        int[] remainingCapacity = new int[n];
        for (int i = 0; i < n; i++) {
            remainingCapacity[i] = sc.nextInt();
        }
        int faultNode = sc.nextInt();
        int needCapacity = sc.nextInt();

        // 记录源点到各个节点的最短路径
        dist = new int[n];
        // 记录节点是否已经找到了最短路径
        visited = new boolean[n];
        // 记录节点的父节点（保存父节点）
        path = new int[n];

        dijkstra(delayMatrix, faultNode);

        int[][] res = new int[n][2];

        for (int i = 0; i < n; i++) {
            res[i][0] = dist[i];
            res[i][1] = i;
        }

        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += remainingCapacity[res[i][1]];
            if (sum >= needCapacity) {
                System.out.print(res[i][1]);
                break;
            } else {
                System.out.print(res[i][1] + " ");
            }
        }

        sc.close();
    }

    // 记录源点到各个节点的最短路径
    int[] dist;
    // 记录节点是否已经找到了最短路径
    boolean[] visited;
    // 记录节点的父节点（保存父节点）
    int[] path;

    public static int INF = 99999;

    // matrix为邻接矩阵，origin为源点
    // 无向图的邻接矩阵是按对角线对称的
    // matrix[i][j]表示节点i到节点j的距离，matrix[i][i] = 0，INF表示无法到达
    public void dijkstra(int[][] matrix, int origin) {
        // 节点的个数
        int nodeNum = matrix.length;

        // 初始化dist数组和path数组
        for (int i = 0; i < nodeNum; i++) {
            if (matrix[origin][i] == -1) {
                dist[i] = INF;
                path[i] = -1;
            } else {
                path[i] = origin;
                dist[i] = matrix[origin][i];
            }
        }

        // 初始化visited数组
        visited[origin] = true;

        // 更新dist数组
        for (int i = 0; i < nodeNum - 1; i++) {
            // 选取下一个访问的节点
            int index = minDistanceNode(dist, visited);
            visited[index] = true;
            for (int j = 0; j < nodeNum; j++) {
                // 如果节点j未被访问且源点经过index节点到节点j的距离小于dist[j]则更新dist[j]
                if (!visited[j] && dist[index] + matrix[index][j] < dist[j]) {
                    path[j] = index;
                    dist[j] = dist[index] + matrix[index][j];
                }
            }
        }
    }

    // 找出dist数组中未被访问的值最小的节点
    public int minDistanceNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

}

