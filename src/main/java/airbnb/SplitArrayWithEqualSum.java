package airbnb;

import java.util.HashSet;

/**
 * 548. 将数组分割成和相等的子数组
 * 给定一个有 n 个整数的数组，你需要找到满足以下条件的三元组 (i, j, k) ：
 *
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * 子数组 (0, i - 1)，(i + 1, j - 1)，(j + 1, k - 1)，(k + 1, n - 1) 的和应该相等。
 * 这里我们定义子数组 (L, R) 表示原数组从索引为L的元素开始至索引为R的元素。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,2,1,2,1,2,1]
 * 输出: True
 * 解释:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 *
 *
 * 注意:
 *
 * 1 <= n <= 2000。
 * 给定数组中的元素会在 [-1,000,000, 1,000,000] 范围内。
 */
public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int j = 3; j < nums.length - 3; j++) {
            HashSet < Integer > set = new HashSet< >();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i])
                    set.add(sum[i - 1]);
            }
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j]))
                    return true;
            }
        }
        return false;
    }
}
/**
 * 方法一：暴力解法 【超出时间限制】
 * 算法
 *
 * 首先需要知道 ii，jj 和 kk 的取值范围，下图显示了 ii，jj 和 kk 能够取得的最小值和最大值。
 *
 *
 *
 * 长度为 nn 的数组，ii，jj 和 kk 的取值分别为：
 *
 * 1 \le i \le n-61≤i≤n−6
 *
 * i+2 \le j \le n-4i+2≤j≤n−4
 *
 * j+2 \le k \le n-2j+2≤k≤n−2
 *
 * 在 ii，jj 和 kk 满足取值条件的情况下，遍历数组 numnum，计算所有可能的分割情况，检查每种分割情况是否满足题意。
 *
 * Java
 *
 * public class Solution {
 *
 *     public int sum(int[] nums, int l, int r) {
 *         int summ = 0;
 *         for (int i = l; i < r; i++)
 *             summ += nums[i];
 *         return summ;
 *     }
 *
 *     public boolean splitArray(int[] nums) {
 *         if (nums.length < 7)
 *             return false;
 *         for (int i = 1; i < nums.length - 5; i++) {
 *             int sum1 = sum(nums, 0, i);
 *             for (int j = i + 2; j < nums.length - 3; j++) {
 *                 int sum2 = sum(nums, i + 1, j);
 *                 for (int k = j + 2; k < nums.length - 1; k++) {
 *                     int sum3 = sum(nums, j + 1, k);
 *                     int sum4 = sum(nums, k + 1, nums.length);
 *                     if (sum1 == sum2 && sum3 == sum4 && sum2 == sum4)
 *                         return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(n^4)O(n
 * 4
 *  )，四层 for 循环。最坏的情况下，每层循环的复杂度都是 O(n)O(n)。
 *
 * 空间复杂度：O(1)O(1)，恒定的额外空间。
 *
 * 方法二：累加和 【超出时间限制】
 * 算法
 *
 * 暴力解法需要求解每个子数组的和。该方法中使用累加和数组计算每个子数组的和，以提高计算效率。其中 sum[i]sum[i] 存储数组 numsnums 从 00 开始，直到第 ii 个元素的累加和。因此 sum\big(subarray(i:j)\big)sum(subarray(i:j)) 等于 sum[j]-sum[i]sum[j]−sum[i]。
 *
 * Java
 *
 * public class Solution {
 *     public boolean splitArray(int[] nums) {
 *         if (nums.length < 7)
 *             return false;
 *         int[] sum = new int[nums.length];
 *         sum[0] = nums[0];
 *         for (int i = 1; i < nums.length; i++) {
 *             sum[i] = sum[i - 1] + nums[i];
 *         }
 *         for (int i = 1; i < nums.length - 5; i++) {
 *             int sum1 = sum[i - 1];
 *             for (int j = i + 2; j < nums.length - 3; j++) {
 *                 int sum2 = sum[j - 1] - sum[i];
 *                 for (int k = j + 2; k < nums.length - 1; k++) {
 *                     int sum3 = sum[k - 1] - sum[j];
 *                     int sum4 = sum[nums.length - 1] - sum[k];
 *                     if (sum1 == sum2 && sum3 == sum4 && sum2 == sum4)
 *                         return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(n^3)O(n
 * 3
 *  )，三层 for 循环，与计算累加和。
 *
 * 空间复杂度：O(n)O(n)，累积和数组 sumsum 长度为 nn。
 *
 * 方法三：改进的累加和 【超出时间限制】
 * 算法
 *
 * 在 方法二 中，如果第一个子数组和第二个子数组的和不相等，则直接停止后面的运算，这在一定程度上提高了 方法二 的计算效率。
 *
 * Java
 *
 * public class Solution {
 *     public boolean splitArray(int[] nums) {
 *         if (nums.length < 7)
 *             return false;
 *
 *         int[] sum = new int[nums.length];
 *         sum[0] = nums[0];
 *         for (int i = 1; i < nums.length; i++) {
 *             sum[i] = sum[i - 1] + nums[i];
 *         }
 *         for (int i = 1; i < nums.length - 5; i++) {
 *             int sum1 = sum[i - 1];
 *             for (int j = i + 2; j < nums.length - 3; j++) {
 *                 int sum2 = sum[j - 1] - sum[i];
 *                 if (sum1 != sum2)
 *                     continue;
 *                 for (int k = j + 2; k < nums.length - 1; k++) {
 *                     int sum3 = sum[k - 1] - sum[j];
 *                     int sum4 = sum[nums.length - 1] - sum[k];
 *                     if (sum3 == sum4 && sum2 == sum4)
 *                         return true;
 *                 }
 *             }
 *         }
 *         return false;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(n^3)O(n
 * 3
 *  )，三层 for 循环。
 *
 * 空间复杂度：O(n)O(n)，累积和数组 sumsum 长度为 nn。
 *
 * 方法四：使用 HashMap 【超出时间限制】
 * 算法
 *
 * 本方法中，使用 mapmap 存储数据，其数据格式为：
 *
 * \big\{csum(i):[i_1,i_2,i_3,....]\big\}{csum(i):[i
 * 1
 * ​
 *  ,i
 * 2
 * ​
 *  ,i
 * 3
 * ​
 *  ,....]}，其中 csum(i)csum(i) 表示数组 numsnums 的前 ii 项累加和，[i_1,i_2,i_3,....][i
 * 1
 * ​
 *  ,i
 * 2
 * ​
 *  ,i
 * 3
 * ​
 *  ,....] 表示该累加和对应序列的最后一个元素索引，即满足 sum=csum(i)sum=csum(i) 的所有 ii。
 *
 * 然后考虑 ii 和 jj 的位置。数组前 j-1j−1 项累加和为：
 *
 * csum(j-1)=sum(part1) + nums[i] + sum(part2)
 * csum(j−1)=sum(part1)+nums[i]+sum(part2)
 *
 * 如果前两个子数组的累加和相等，则有
 *
 * csum'(j-1) = csum(i-1) + nums[i] + csum(i-1) = 2csum(i-1) + nums[i]
 * csum
 * ′
 *  (j−1)=csum(i−1)+nums[i]+csum(i−1)=2csum(i−1)+nums[i]
 *
 * 遍历数组 numsnums，确定第一个分割点 ii 的位置，然后在 mapmap 中查找所有满足 csum'(j-1) = 2csum(i-1) + nums[i]csum
 * ′
 *  (j−1)=2csum(i−1)+nums[i] 的 jj。再遍历所有的 jj，寻找第三个分割点，使得第三个和第四个子数组的累加和与第一个子数组累加和相同。
 *
 * 与前面类似，数组前 k-1k−1 个元素的累加和为：
 *
 * csum(k-1) = sum(part1) + nums[i] + sum(part2) + nums[j] + sum(part3)
 * csum(k−1)=sum(part1)+nums[i]+sum(part2)+nums[j]+sum(part3)
 *
 * 为了保证第三个子数组的累加和与前两个子数组相同，必须满足：
 *
 * csum'(k-1) = 3*csum(i-1) + nums[i] + nums[j]
 * csum
 * ′
 *  (k−1)=3∗csum(i−1)+nums[i]+nums[j]
 *
 * 数组 numsnums 所有元素的累加和为：
 *
 * csum(end) = sum(part1) + nums[i] + sum(part2) + nums[j] + sum(part3) + nums[k] + sum(part4)
 * csum(end)=sum(part1)+nums[i]+sum(part2)+nums[j]+sum(part3)+nums[k]+sum(part4)
 *
 * 同理，为了保证第四个子数组的累加和与前三个子数组相同，必须满足：
 *
 * csum'(end) = 4*csum(i-1) + nums[i] + nums[j] + nums[k]
 * csum
 * ′
 *  (end)=4∗csum(i−1)+nums[i]+nums[j]+nums[k]
 *
 * 对于每种分割情况，只需要在 mapmap 中查找是否存在满足条件的累加和即可，不需要遍历所有的 (i, j, k)(i,j,k) 组合，也不需要计算每个子数组的累加和。因此，这大大减少了计算量。
 *
 * Java
 *
 * public class Solution {
 *     public boolean splitArray(int[] nums) {
 *         HashMap < Integer, ArrayList < Integer >> map = new HashMap < > ();
 *         int summ = 0, tot = 0;
 *         for (int i = 0; i < nums.length; i++) {
 *             summ += nums[i];
 *             if (map.containsKey(summ))
 *                 map.get(summ).add(i);
 *             else {
 *                 map.put(summ, new ArrayList < Integer > ());
 *                 map.get(summ).add(i);
 *             }
 *             tot += nums[i];
 *         }
 *         summ = nums[0];
 *         for (int i = 1; i < nums.length - 5; i++) {
 *             if (map.containsKey(2 * summ + nums[i])) {
 *                 for (int j: map.get(2 * summ + nums[i])) {
 *                     j++;
 *                     if (j > i + 1 && j < nums.length - 3 && map.containsKey(3 * summ + nums[i] + nums[j])) {
 *                         for (int k: map.get(3 * summ + nums[j] + nums[i])) {
 *                             k++;
 *                             if (k < nums.length - 1 && k > j + 1 && 4 * summ + nums[i] + nums[j] + nums[k] == tot)
 *                                 return true;
 *                         }
 *                     }
 *                 }
 *             }
 *             summ += nums[i];
 *         }
 *         return false;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(n^3)O(n
 * 3
 *  )，三层嵌套循环。最坏情况下，每层循环的复杂度都是 O(n)O(n)，例如 [0,0,0....,1,1,1,1,1,1,1]。
 *
 * 空间复杂度：O(n)O(n)，大小为 nn 的 HashMap。
 *
 * 方法五：HashSet+累加和 【通过】
 * 算法
 *
 * 本方法中，首先计算累加和数组 sumsum，其中 sum[i]sum[i] 表示数组 numsnums 前 ii 项和。然后遍历第二个分割点 jj 所有可能的位置。对每一个 jj，首先计算其左边分割点的位置 ii，使得第一个子数组和第二个子数组的和相等，即满足 sum[i-1] = sum [j-1] - sum[i]sum[i−1]=sum[j−1]−sum[i]，并将该累加和存储到 HashSet 中（对每个 jj，都会创建一个新的 HashSet）。因此，HashSet 中的累加和表示当中间分割点为 jj 时，怎样的累加和会让第一个子数组和第二个子数组之和相等。
 *
 * 然后计算 jj 右边分割点 kk 的位置，使得第三个子数组的和与第四个子数组的和相等，即满足 sum[n-1]-sum[k]=sum[k-1] - sum[j]sum[n−1]−sum[k]=sum[k−1]−sum[j]。再到 HashSet 中查找是否存在相等的子数组和。如果存在，则找到满足条件的三元组 (i, j, k)(i,j,k)，否则不存在这样的分割方法。
 *
 * 下面过程展示了一个寻找分割点的例子：
 *
 *
 * 1 / 10
 *
 * Java
 *
 * public class Solution {
 *     public boolean splitArray(int[] nums) {
 *         if (nums.length < 7)
 *             return false;
 *         int[] sum = new int[nums.length];
 *         sum[0] = nums[0];
 *         for (int i = 1; i < nums.length; i++) {
 *             sum[i] = sum[i - 1] + nums[i];
 *         }
 *         for (int j = 3; j < nums.length - 3; j++) {
 *             HashSet < Integer > set = new HashSet < > ();
 *             for (int i = 1; i < j - 1; i++) {
 *                 if (sum[i - 1] == sum[j - 1] - sum[i])
 *                     set.add(sum[i - 1]);
 *             }
 *             for (int k = j + 2; k < nums.length - 1; k++) {
 *                 if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j]))
 *                     return true;
 *             }
 *         }
 *         return false;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(n^2)O(n
 * 2
 *  )，一个外层循环和两个内层循环。
 *
 * 空间复杂度：O(n)O(n)，大小为 nn 的 HashSet。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/split-array-with-equal-sum/solution/jiang-shu-zu-fen-ge-cheng-he-xiang-deng-de-zi-shu-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */