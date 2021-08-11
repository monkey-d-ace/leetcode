package airbnb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 760. 找出变位映射
 * 给定两个列表 Aand B，并且 B 是 A 的变位（即 B 是由 A 中的元素随机排列后组成的新列表）。
 *
 * 我们希望找出一个从 A 到 B 的索引映射 P 。一个映射 P[i] = j 指的是列表 A 中的第 i 个元素出现于列表 B 中的第 j 个元素上。
 *
 * 列表 A 和 B 可能出现重复元素。如果有多于一种答案，输出任意一种。
 *
 * 例如，给定
 *
 * A = [12, 28, 46, 32, 50]
 * B = [50, 12, 32, 46, 28]
 *
 *
 * 需要返回
 *
 * [1, 4, 3, 2, 0]
 * P[0] = 1 ，因为 A 中的第 0 个元素出现于 B[1]，而且 P[1] = 4 因为 A 中第 1 个元素出现于 B[4]，以此类推。
 *
 *
 *
 * 注：
 *
 * A, B 有相同的长度，范围为 [1, 100]。
 * A[i], B[i] 都是范围在 [0, 10^5] 的整数。
 *
 */
@Component
public class FindAnagramMappings {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> D = new HashMap();
        for (int i = 0; i < B.length; ++i)
            D.put(B[i], i);

        int[] ans = new int[A.length];
        int t = 0;
        for (int x: A)
            ans[t++] = D.get(x);
        return ans;
    }
}
/**
 * 方法：哈希表 [通过]
 * 以 A=[12，28，46]，B=[46，12，28] 为例。我们想知道 12，28，46 在 B 中的位置，如果有一个字典（哈希表）D = {46: 0, 12: 1, 28: 2}，那么这个问题可以很轻松的被解决。
 *
 * 算法：
 *
 * 按上述创建哈希表 D，则答案为 D[A[i]]，i = 0, 1, ... 组成的列表。
 *
 * PythonJava
 *
 * class Solution {
 *     public int[] anagramMappings(int[] A, int[] B) {
 *         Map<Integer, Integer> D = new HashMap();
 *         for (int i = 0; i < B.length; ++i)
 *             D.put(B[i], i);
 *
 *         int[] ans = new int[A.length];
 *         int t = 0;
 *         for (int x: A)
 *             ans[t++] = D.get(x);
 *         return ans;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N)。其中 NN 为 AA 的长度。
 * 空间复杂度：O(N)O(N)，哈希表所使用的空间。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/find-anagram-mappings/solution/zhao-chu-bian-wei-ying-she-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */