package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的边界
 *
 * 给定一棵二叉树，以逆时针顺序从根开始返回其边界。边界按顺序包括左边界、叶子结点和右边界而不包括重复的结点。 (结点的值可能重复)
 *
 * 左边界的定义是从根到最左侧结点的路径。右边界的定义是从根到最右侧结点的路径。若根没有左子树或右子树，则根自身就是左边界或右边界。注意该定义只对输入的二叉树有效，而对子树无效。
 *
 * 最左侧结点的定义是：在左子树存在时总是优先访问，如果不存在左子树则访问右子树。重复以上操作，首先抵达的结点就是最左侧结点。
 *
 * 最右侧结点的定义方式相同，只是将左替换成右。
 *
 * 示例 1
 *
 * 输入:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * 输出:
 * [1, 3, 4, 2]
 *
 * 解析:
 * 根不存在左子树，故根自身即为左边界。
 * 叶子结点是3和4。
 * 右边界是1，2，4。注意逆时针顺序输出需要你输出时调整右边界顺序。
 * 以逆时针顺序无重复地排列边界，得到答案[1,3,4,2]。
 *  
 *
 * 示例 2
 *
 * 输入:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * 输出:
 * [1,2,4,7,8,9,10,6,3]
 *
 * 解析:
 * 左边界是结点1,2,4。(根据定义，4是最左侧结点)
 * 叶子结点是结点4,7,8,9,10。
 * 右边界是结点1,3,6,10。(10是最右侧结点)
 * 以逆时针顺序无重复地排列边界，得到答案 [1,2,4,7,8,9,10,6,3]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boundary-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BoundaryOfBinaryTree {

    public boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    public void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        addLeaves(res, root);
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }
}
/**
 * 方法 1：简单解法
 * 算法
 *
 * 一个简单的方法是将问题划分成三个子问题：左边界、叶子节点和右边界。
 *
 * 左边界：我们沿左边遍历这棵树，不断向 res 数组中添加节点，并保证当前节点不是叶子节点。如果位于某个节点，我们发现不存在左孩子，但存在右孩子，我们就将右孩子放入 res 中并重复过程。下面的模拟描述了这个过程。
 *
 * 叶子节点：我们调用递归程序 addLeaves(res, root)，每次调用改变根节点。如果当前节点是叶子节点，就会加入 res 数组；否则，我们递归调用左孩子作为新根节点进行递归，然后是右孩子。下面的过程模拟了这个过程。
 *
 * 右边界：和处理左边界同样的步骤。但此时我们沿着右边遍历。如果不存在右孩子，我们就向左孩子移动。同时，不直接将访问到的元素放入 res 数组中，而是放入一个栈，在完成遍历之后从栈中弹出元素并加入 res 数组。下面的过程模拟了这个步骤。
 *
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N)，一次完整的叶子节点遍历，和两次深度的遍历。
 * 空间复杂度：O(N)O(N)，res 和 stack 的空间开销。
 *
 * 方法 2：先序遍历
 * 问题描述很类似于先序遍历。实际上，遍历的顺序是一致的（除去右边界节点，它们是逆序的）。因此，我们只需要上述结果的顶点，属于左边界或者叶子节点或者右边界上。
 *
 * 为了区别这些节点，我们用 flag 符号：
 *
 * flag = 0：根节点
 * flag = 1：左边界节点
 * flag = 2：右边界节点
 * flag = 3 ：其它（中间节点）
 *
 * 我们使用三个列表 \text{left_boundary}，\text{right_boundary} 和 \text{leaves}leaves 存储对应的节点。
 *
 * 我们按照正常的后序遍历的顺序，但当调用递归程序处理左孩子或者右孩子时，我们同时更新 flag 信息，表明这个节点孩子的类型。
 *
 * 当前节点的左孩子，我们使用函数 leftChildFlag(node, flag)。当处理左孩子时，下面可能的情况，可以通过上图来区分：
 *
 * 当前节点是左边界节点：左孩子也是左边界节点。例如，图中 E 和 J 的关系。
 * 当前节点是根节点：左孩子也是左边界节点。例如，图中 A 和 B 的关系。
 * 当前节点是右边界节点：如果右孩子不存在那么左孩子就是右边界节点。例如，上图中的 G 和 N。但是如果右孩子存在，左孩子只能作为中间节点，如图中 C 和 F。
 *
 * 相似地，也会有 rightChildFlag(node, flag) 来判断当前节点的右孩子：
 *
 * 当前节点是右边界节点：右孩子也是右边界节点。例如，图中 C 和 G 的关系。
 * 当前节点是根节点：右孩子也是右边界节点。例如，图中 A 和 C 的关系。
 * 当前节点是左边界节点：如果左孩子不存在那么右孩子就是右边界节点。例如，上图中的 B 和 E。但是如果右孩子存在，左孩子只能作为中间节点。
 * 使用这些信息，我们更新 flag 然后用来决定那些节点会被加入到输出列表中。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/boundary-of-binary-tree/solution/er-cha-shu-de-bian-jie-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */