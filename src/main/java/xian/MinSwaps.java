package xian;

/**
 * 最少交换次数来组合所有的 1
 * 给出一个二进制数组 data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。
 *
 * 示例 1：
 *
 * 输入：[1,0,1,0,1]
 * 输出：1
 * 解释：
 * 有三种可能的方法可以把所有的 1 组合在一起：
 * [1,1,1,0,0]，交换 1 次；
 * [0,1,1,1,0]，交换 2 次；
 * [0,0,1,1,1]，交换 1 次。
 * 所以最少的交换次数为 1。
 * 示例 2：
 *
 * 输入：[0,0,0,1,0]
 * 输出：0
 * 解释：
 * 由于数组中只有一个 1，所以不需要交换。
 * 示例 3：
 *
 * 输入：[1,0,1,0,1,0,0,1,1,0,1]
 * 输出：3
 * 解释：
 * 交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
 *  
 *
 * 提示：
 *
 * 1 <= data.length <= 10^5
 * 0 <= data[i] <= 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-group-all-1s-together
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSwaps {
    public int minSwaps(int[] data) {
        int k = 0;
        int i = 0;
        // k个1
        while (i < data.length) {
            k += data[i];
            i++;
        }
        // 窗口1计数
        int ones = 0;
        for (int j = 0; j < k; j++) {
            ones += data[j];
        }
        int N = ones;
        int left = 0, right = k - 1;
        while (right < data.length - 1) {
            right++;
            ones += data[right] - data[left];
            N = (ones > N) ? ones : N;
            left++;
        }
        return k - N;
    }
}
/**
 * 思路：
 * 1，首先统计1的个数，假设为K，那么最终所有的1聚集在一起的时候一定是一个长度为K的连续区间或者说窗口
 * 2，我们用一个大小为K的窗口从左到右扫面一遍数组，找到窗口内1的个数最多的窗口即是答案。
 * 3，假设最终某个包含1最多的窗口内1的个数为N，那么调整数为K - N，也就是把K - N个0与窗口外的同样数目的1调换位置即可。
 *
 */