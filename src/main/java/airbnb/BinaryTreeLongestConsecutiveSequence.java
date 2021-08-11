package airbnb;

import common.TreeNode;
import org.springframework.stereotype.Component;

/**
 * 298. 二叉树最长连续序列
 * 给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。
 *
 * 该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。
 *
 * 这个最长连续的路径，必须从父结点到子结点，反过来是不可以的。
 *
 * 示例 1：
 *
 * 输入:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 *
 * 输出: 3
 *
 * 解析: 当中，最长连续序列是 3-4-5，所以返回结果为 3
 * 示例 2：
 *
 * 输入:
 *
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 *
 * 输出: 2
 *
 * 解析: 当中，最长连续序列是 2-3。注意，不是 3-2-1，所以返回 2。
 */
@Component
public class BinaryTreeLongestConsecutiveSequence {
    private int maxLength = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, null, 0);
        return maxLength;
    }

    private void dfs(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength = Math.max(maxLength, length);
        dfs(p.left, p, length);
        dfs(p.right, p, length);
    }
}
