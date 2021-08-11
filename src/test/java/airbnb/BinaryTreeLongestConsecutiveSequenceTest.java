package airbnb;

import common.TreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BinaryTreeLongestConsecutiveSequenceTest {

    @Test
    public void testBinaryTreeLongestConsecutiveSequence() {
        BinaryTreeLongestConsecutiveSequence binaryTreeLongestConsecutiveSequence = new BinaryTreeLongestConsecutiveSequence();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        Assert.assertEquals(3, binaryTreeLongestConsecutiveSequence.longestConsecutive(root));

        binaryTreeLongestConsecutiveSequence = new BinaryTreeLongestConsecutiveSequence();
        root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(1);
        Assert.assertEquals(2, binaryTreeLongestConsecutiveSequence.longestConsecutive(root));
    }
}
