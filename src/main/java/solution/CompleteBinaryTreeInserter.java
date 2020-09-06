package solution;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 919. 完全二叉树插入器
 *
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *  
 *
 * 示例 1：
 *
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * 示例 2：
 *
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *  
 *
 * 提示：
 *
 * 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
 * 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 给定节点或插入节点的每个值都在 0 到 5000 之间。
 *
 */
public class CompleteBinaryTreeInserter {
    TreeNode root;
    Deque<TreeNode> deque;
    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // BFS to populate deque
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null) {
                deque.offerLast(node);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null) {
            node.left = deque.peekLast();
        } else {
            node.right = deque.peekLast();
            deque.pollFirst();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
/**
 * 复杂度分析
 * 方法 1：双端队列
 * 想法
 *
 * 将所有节点编号，按照从上到下从左到右的顺序。
 *
 * 在每个插入步骤中，我们希望插入到一个编号最小的节点（这样有 0 或者 1 个孩子）。
 *
 * 通过维护一个 deque （双端队列），保存这些节点的编号，我们可以解决这个问题。插入一个节点之后，将成为最高编号的节点，并且没有孩子，所以插入到队列的后端。为了找到最小数字的节点，我们从队列前端弹出元素。
 *
 * 算法
 *
 * 首先，通过广度优先搜索将 deque 中插入含有 0 个或者 1 个孩子的节点编号。
 *
 * 然后插入节点，父亲是 deque 的第一个元素，我们将新节点加入我们的 deque。
 *
 * 时间复杂度：预处理 O(N)O(N)，其中 NN 是树上节点编号。每个插入步骤是 O(1)O(1)。
 * 空间复杂度：O(N_\text{cur})O(N
 * cur
 * ​
 *  )，其中当前插入操作树的大小为 N_{\text{cur}}N
 * cur
 * ​
 */