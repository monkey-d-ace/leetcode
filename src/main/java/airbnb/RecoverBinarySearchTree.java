package airbnb;

import common.TreeNode;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 *
 *
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 *
 * 提示：
 *
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 */
public class RecoverBinarySearchTree {
    TreeNode pre;
    TreeNode first;
    TreeNode two;

    public void recoverTree(TreeNode root) {
        findErrorNodes(root);
        int tmp = first.val;
        first.val = two.val;
        two.val = tmp;
    }

    private void findErrorNodes(TreeNode root) {
        if (root == null) return;
        findErrorNodes(root.left);

        if (pre != null && pre.val > root.val) {
            two = root;
            if (first != null) return;
            first = pre;
        }
        pre = root;

        findErrorNodes(root.right);
    }
}
/**
 * 方法一：显式中序遍历
 * 思路与算法
 *
 * 我们需要考虑两个节点被错误地交换后对原二叉搜索树造成了什么影响。对于二叉搜索树，我们知道如果对其进行中序遍历，得到的值序列是递增有序的，而如果我们错误地交换了两个节点，等价于在这个值序列中交换了两个值，破坏了值序列的递增性。
 *
 * 我们来看下如果在一个递增的序列中交换两个值会造成什么影响。假设有一个递增序列 a=[1,2,3,4,5,6,7]a=[1,2,3,4,5,6,7]。如果我们交换两个不相邻的数字，例如 22 和 66，原序列变成了 a=[1,6,3,4,5,2,7]a=[1,6,3,4,5,2,7]，那么显然序列中有两个位置不满足 a_i<a_{i+1}a
 * i
 * ​
 *  <a
 * i+1
 * ​
 *  ，在这个序列中体现为 6>36>3，5>25>2，因此只要我们找到这两个位置，即可找到被错误交换的两个节点。如果我们交换两个相邻的数字，例如 22 和 33，此时交换后的序列只有一个位置不满足 a_i<a_{i+1}a
 * i
 * ​
 *  <a
 * i+1
 * ​
 *  。因此整个值序列中不满足条件的位置或者有两个，或者有一个。
 *
 * 至此，解题方法已经呼之欲出了：
 *
 * 找到二叉搜索树中序遍历得到值序列的不满足条件的位置。
 * 如果有两个，我们记为 ii 和 jj（i<ji<j 且 a_i>a_{i+1}\ \&\&\ a_j>a_{j+1}a
 * i
 * ​
 *  >a
 * i+1
 * ​
 *   && a
 * j
 * ​
 *  >a
 * j+1
 * ​
 *  )，那么对应被错误交换的节点即为 a_ia
 * i
 * ​
 *   对应的节点和 a_{j+1}a
 * j+1
 * ​
 *   对应的节点，我们分别记为 xx 和 yy。
 * 如果有一个，我们记为 ii，那么对应被错误交换的节点即为 a_ia
 * i
 * ​
 *   对应的节点和 a_{i+1}a
 * i+1
 * ​
 *   对应的节点，我们分别记为 xx 和 yy。
 * 交换 xx 和 yy 两个节点即可。
 * 实现部分，本方法开辟一个新数组 \textit{nums}nums 来记录中序遍历得到的值序列，然后线性遍历找到两个位置 ii 和 jj，并重新遍历原二叉搜索树修改对应节点的值完成修复，具体实现可以看下面的代码。
 *
 * 代码
 *
 * JavaC++JavaScriptGolangC
 *
 * class Solution {
 *     public void recoverTree(TreeNode root) {
 *         List<Integer> nums = new ArrayList<Integer>();
 *         inorder(root, nums);
 *         int[] swapped = findTwoSwapped(nums);
 *         recover(root, 2, swapped[0], swapped[1]);
 *     }
 *
 *     public void inorder(TreeNode root, List<Integer> nums) {
 *         if (root == null) {
 *             return;
 *         }
 *         inorder(root.left, nums);
 *         nums.add(root.val);
 *         inorder(root.right, nums);
 *     }
 *
 *     public int[] findTwoSwapped(List<Integer> nums) {
 *         int n = nums.size();
 *         int x = -1, y = -1;
 *         for (int i = 0; i < n - 1; ++i) {
 *             if (nums.get(i + 1) < nums.get(i)) {
 *                 y = nums.get(i + 1);
 *                 if (x == -1) {
 *                     x = nums.get(i);
 *                 } else {
 *                     break;
 *                 }
 *             }
 *         }
 *         return new int[]{x, y};
 *     }
 *
 *     public void recover(TreeNode root, int count, int x, int y) {
 *         if (root != null) {
 *             if (root.val == x || root.val == y) {
 *                 root.val = root.val == x ? y : x;
 *                 if (--count == 0) {
 *                     return;
 *                 }
 *             }
 *             recover(root.right, count, x, y);
 *             recover(root.left, count, x, y);
 *         }
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N)，其中 NN 为二叉搜索树的节点数。中序遍历需要 O(N)O(N) 的时间，判断两个交换节点在最好的情况下是 O(1)O(1)，在最坏的情况下是 O(N)O(N)，因此总时间复杂度为 O(N)O(N)。
 * 空间复杂度：O(N)O(N)。我们需要用 \textit{nums}nums 数组存储树的中序遍历列表。
 * 方法二：隐式中序遍历
 * 思路与算法
 *
 * 方法一是显式地将中序遍历的值序列保存在一个 \textit{nums}nums 数组中，然后再去寻找被错误交换的节点，但我们也可以隐式地在中序遍历的过程就找到被错误交换的节点 xx 和 yy。
 *
 * 具体来说，由于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最多两个位置不满足条件，因此在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点 \textit{pred}pred，然后在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历。
 *
 * 这样我们就可以在中序遍历中直接找到被错误交换的两个节点 xx 和 yy，不用显式建立 \textit{nums}nums 数组。
 *
 * 中序遍历的实现有迭代和递归两种等价的写法，在本方法中提供迭代实现的写法。使用迭代实现中序遍历需要手动维护栈。
 *
 *
 * 1 / 9
 *
 * 代码
 *
 * JavaC++JavaScriptGolangC
 *
 * class Solution {
 *     public void recoverTree(TreeNode root) {
 *         Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
 *         TreeNode x = null, y = null, pred = null;
 *
 *         while (!stack.isEmpty() || root != null) {
 *             while (root != null) {
 *                 stack.push(root);
 *                 root = root.left;
 *             }
 *             root = stack.pop();
 *             if (pred != null && root.val < pred.val) {
 *                 y = root;
 *                 if (x == null) {
 *                     x = pred;
 *                 } else {
 *                     break;
 *                 }
 *             }
 *             pred = root;
 *             root = root.right;
 *         }
 *
 *         swap(x, y);
 *     }
 *
 *     public void swap(TreeNode x, TreeNode y) {
 *         int tmp = x.val;
 *         x.val = y.val;
 *         y.val = tmp;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：最坏情况下（即待交换节点为二叉搜索树最右侧的叶子节点）我们需要遍历整棵树，时间复杂度为 O(N)O(N)，其中 NN 为二叉搜索树的节点个数。
 * 空间复杂度：O(H)O(H)，其中 HH 为二叉搜索树的高度。中序遍历的时候栈的深度取决于二叉搜索树的高度。
 * 方法三：Morris 中序遍历
 * 思路与算法
 *
 * 方法二中我们不再显示的用数组存储中序遍历的值序列，但是我们会发现我们仍需要 O(H)O(H) 的栈空间，无法满足题目的进阶要求，那么该怎么办呢？这里向大家介绍一种不同于平常递归或迭代的遍历二叉树的方法：Morris 遍历算法，该算法能将非递归的中序遍历空间复杂度降为 O(1)O(1)。
 *
 * Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 xx）：
 *
 * 如果 xx 无左孩子，则访问 xx 的右孩子，即 x = x.\textit{right}x=x.right。
 * 如果 xx 有左孩子，则找到 xx 左子树上最右的节点（即左子树中序遍历的最后一个节点，xx 在中序遍历中的前驱节点），我们记为 \textit{predecessor}predecessor。根据 \textit{predecessor}predecessor 的右孩子是否为空，进行如下操作。
 * 如果 \textit{predecessor}predecessor 的右孩子为空，则将其右孩子指向 xx，然后访问 xx 的左孩子，即 x = x.\textit{left}x=x.left。
 * 如果 \textit{predecessor}predecessor 的右孩子不为空，则此时其右孩子指向 xx，说明我们已经遍历完 xx 的左子树，我们将 \textit{predecessor}predecessor 的右孩子置空，然后访问 xx 的右孩子，即 x = x.\textit{right}x=x.right。
 * 重复上述操作，直至访问完整棵树。
 * 其实整个过程我们就多做一步：将当前节点左子树中最右边的节点指向它，这样在左子树遍历完成后我们通过这个指向走回了 xx，且能再通过这个知晓我们已经遍历完成了左子树，而不用再通过栈来维护，省去了栈的空间复杂度。
 *
 * 了解完这个算法以后，其他地方与方法二并无不同，我们同样也是维护一个 \textit{pred}pred 变量去比较即可，具体实现可以看下面的代码，这里不再赘述。
 *
 *
 * 1 / 11
 *
 * 代码
 *
 * JavaC++JavaScriptGolangC
 *
 * class Solution {
 *     public void recoverTree(TreeNode root) {
 *         TreeNode x = null, y = null, pred = null, predecessor = null;
 *
 *         while (root != null) {
 *             if (root.left != null) {
 *                 // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
 *                 predecessor = root.left;
 *                 while (predecessor.right != null && predecessor.right != root) {
 *                     predecessor = predecessor.right;
 *                 }
 *
 *                 // 让 predecessor 的右指针指向 root，继续遍历左子树
 *                 if (predecessor.right == null) {
 *                     predecessor.right = root;
 *                     root = root.left;
 *                 }
 *                 // 说明左子树已经访问完了，我们需要断开链接
 *                 else {
 *                     if (pred != null && root.val < pred.val) {
 *                         y = root;
 *                         if (x == null) {
 *                             x = pred;
 *                         }
 *                     }
 *                     pred = root;
 *
 *                     predecessor.right = null;
 *                     root = root.right;
 *                 }
 *             }
 *             // 如果没有左孩子，则直接访问右孩子
 *             else {
 *                 if (pred != null && root.val < pred.val) {
 *                     y = root;
 *                     if (x == null) {
 *                         x = pred;
 *                     }
 *                 }
 *                 pred = root;
 *                 root = root.right;
 *             }
 *         }
 *         swap(x, y);
 *     }
 *
 *     public void swap(TreeNode x, TreeNode y) {
 *         int tmp = x.val;
 *         x.val = y.val;
 *         y.val = tmp;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N)，其中 NN 为二叉搜索树的高度。Morris 遍历中每个节点会被访问两次，因此总时间复杂度为 O(2N)=O(N)O(2N)=O(N)。
 * 空间复杂度：O(1)O(1)。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */