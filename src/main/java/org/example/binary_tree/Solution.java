package org.example.binary_tree;

import java.util.*;

public class Solution {

    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    public void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();

            while (size > 0) {
                TreeNode node = queue.remove();
                tmp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            result.add(tmp);
        }

        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = levelOrder(root);
        Collections.reverse(result);
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list : lists) {
            result.add(list.get(list.size() - 1));
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list : lists) {
            // int精度不够
            long sum = 0;
            for (Integer integer : list) {
                sum += integer;
            }
            result.add(sum / (double) list.size());
        }
        return result;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            while (size > 0) {
                Node node = queue.remove();
                tmp.add(node.val);
                for (Node child : node.children) {
                    if (child != null) {
                        queue.add(child);
                    }
                }
                size--;
            }
            result.add(tmp);
        }
        return result;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list : lists) {
            result.add(Collections.max(list));
        }
        return result;
    }

    public MyNode connect(MyNode root) {
        if (root == null) {
            return root;
        }
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                MyNode node1 = queue.remove();
                MyNode node2;
                if (size == 1) {
                    node2 = null;
                } else {
                    node2 = queue.peek();
                }
                node1.next = node2;
                if (node1.left != null) {
                    queue.add(node1.left);
                }
                if (node1.right != null) {
                    queue.add(node1.right);
                }
                size--;
            }
        }
        return root;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.remove();
                // 当我们找到一个叶子节点时，直接返回这个叶子节点的深度。广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && left == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }

        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);

        return outside && inside;
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        if (root.children != null) {
            for (Node child : root.children) {
                depth = Math.max(depth, maxDepth(child));
            }
        }
        return depth + 1;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : Math.max(leftHeight, rightHeight) + 1;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        traversal(root, path, result);
        return result;
    }

    public void traversal(TreeNode node, List<Integer> path, List<String> result) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                stringBuilder.append(path.get(i)).append("->");
            }
            stringBuilder.append(path.get(path.size() - 1));
            result.add(stringBuilder.toString());
            return;
        }
        if (node.left != null) {
            traversal(node.left, path, result);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            traversal(node.right, path, result);
            path.remove(path.size() - 1);
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        int mid = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            mid = root.left.val;
        }
        int sum = left + right + mid;
        return sum;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class MyNode {
    public int val;
    public MyNode left;
    public MyNode right;
    public MyNode next;

    public MyNode() {
    }

    public MyNode(int _val) {
        val = _val;
    }

    public MyNode(int _val, MyNode _left, MyNode _right, MyNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
