package dfs;

import common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class PseudoPalindromicPathsInABinaryTreeTest {

    @Test
    public void pseudoPalindromicPaths() {
        PseudoPalindromicPathsInABinaryTree tree = new PseudoPalindromicPathsInABinaryTree();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        assertEquals(2, tree.pseudoPalindromicPaths(root));
    }
}