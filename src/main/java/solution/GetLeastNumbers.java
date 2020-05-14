package solution;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 面试题40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * 通过次数18,607提交次数30,333
 * 在真实的面试中遇到过这道题？
 */
public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        int[] ans = new int[k];
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });
        for (int i : arr) {
            if (queue.size() < k) {
                queue.add(i);
            } else {
                if (queue.peek() > i) {
                    queue.poll();
                    queue.offer(i);
                }
            }
        }

        int i = 0;
        for (int temp : queue) {
            ans[i] = temp;
            i += 1;
        }
        return ans;
    }
}
