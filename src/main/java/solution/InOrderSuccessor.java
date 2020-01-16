package solution;

/**
 * 二叉搜索树中的顺序后继
 * 给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。
 *
 * 结点 p 的后继是值比 p.val 大的结点中键值最小的结点。
 *
 * 输入: root = [2,1,3], p = 1
 * 输出: 2
 * 解析: 这里 1 的顺序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * 输出: null
 * 解析: 因为给出的结点没有顺序后继，所以答案就返回 null 了。
 *
 * 注意:
 *
 * 假如给出的结点在该树中没有顺序后继的话，请返回 null
 * 我们保证树中每个结点的值是唯一的
 */
public class InOrderSuccessor {

    public static void main(String[] args) {
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(1);
        t.right = new TreeNode(3);
        System.out.println(inorderSuccessor(t, new TreeNode(1)).val);
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;
        TreeNode cur = root;
        TreeNode res = null;
        while (cur != null) {
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                if (res == null || res.val > cur.val) {
                    res = cur;
                }
                cur = cur.left;
            }
        }
        return res;
    }
}
/**
 * 方法1：递归执行中序遍历，获取list，得到p的下一个。时间O(N)，空间O(N)
 * 方法2：
 * 递归执行中序遍历，在递归过程中获取x的下一个。如果当前值是<=x的，那么根据BST的特性只需要在右子树中找。如果当前值>x，则当前值有可能，它的左子树也有可能有更小的但是也>x的，对左子递归后，选择更接近的（更小的).
 * 时间O(logN)，空间O(logN)调用栈的深度。
 *
 * 方法3：循环实现
 * 如果当前值是<=x的，那么根据BST的特性只需要在右子树中找：cur=cur.right。
 * 如果当前值>x，则当前值有可能，它的左子树也有可能有更小的但是也>x的。则每次走入这个分支时，当前点是一个候选点，记录该节点的值和历史最小节点的值。
 * 时间O(logN)，空间O(1）
 *
 */