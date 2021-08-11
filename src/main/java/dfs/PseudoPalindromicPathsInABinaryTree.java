package dfs;

import common.TreeNode;

public class PseudoPalindromicPathsInABinaryTree {
    private int sum = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int temp) {
        if (node == null) return;
        temp ^= (1 << node.val);
        if (node.left == null && node.right == null) {
            if (temp == 0 || (temp & (temp - 1)) == 0) {
                sum++;
            }
            return;
        }
        dfs(node.left, temp);
        dfs(node.right, temp);
    }
}
