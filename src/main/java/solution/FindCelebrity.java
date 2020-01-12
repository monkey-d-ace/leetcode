package solution;

/**
 * 搜寻名人
 *
 * 假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
 *
 * 现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。
 *
 * 在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。
 *
 * 派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。
 *
 * 注意:
 *
 * 该有向图是以邻接矩阵的形式给出的，是一个 n × n 的矩阵， a[i][j] = 1 代表 i 与 j 认识，a[i][j] = 0 则代表 i 与 j 不认识。
 * 请记住，您是无法直接访问邻接矩阵的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-celebrity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCelebrity {
    public static void main(String[] args) {

    }

    private static int findCelebrity(int n) {
        // 先找到名人的候选下标
        int res = 0;
        for (int i = 1; i < n; i++) {
            // 贪心法
            if (knows(res, i))
                res = i;
        }

        // 判断是否符合条件
        for (int i = 0; i < n; i++) {
            if (i != res) {
                if (!knows(i, res)) {
                    return -1;
                }
                if (knows(res, i)) {
                    return -1;
                }
            }
        }
        return res;
    }

    private static boolean knows(int i, int j) {
        return true;
    }
}
/**
 * 思路很简单：检查每个人判断其是不是认识除自己以外的其他人，或者是否有其他人不认识自己（两点只要满足一条那么即可断定当前这个人不是名人）
 */
