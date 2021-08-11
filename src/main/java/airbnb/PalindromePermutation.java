package airbnb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 266. 回文排列
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 *
 * 示例 1：
 *
 * 输入: "code"
 * 输出: false
 * 示例 2：
 *
 * 输入: "aab"
 * 输出: true
 * 示例 3：
 *
 * 输入: "carerac"
 * 输出: true
 */
@Component
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (char key : map.keySet()) {
            count += map.get(key) % 2;
        }
        return count <= 1;
    }
}
/**
 * 方法一：穷举
 * 如果一个字符串可以组成一个回文串，那么：(1) 如果它的长度为偶数，那么每个字符都必须出现偶数次；(2) 如果它的长度为奇数，那么除了一个字符出现奇数次以外，其它的字符都必须出现偶数次。因此可以总结得到，如果一个字符串可以组成一个回文串，那么出现奇数次的字符的数量不能超过 1。
 *
 * 由于字符串中出现的字符的 ASCII 码在 0 到 127 之间，因此我们可以枚举所有的字符，对于每一个字符 \mathrm{c}c，我们找出它在字符串中出现的次数 \mathrm{ct}ct，如果 \mathrm{ct}ct 为奇数，那么我们将计数器 \mathrm{count}count 的值增加 1。如果在某一个时刻 \mathrm{count}count 的值大于 1，那么说明至少有两个字符出现了奇数次，因此字符串就不能组成一个回文串。如果在枚举完成后，\mathrm{count}count 的值仍然小于等于 1，那么字符串就可以组成一个回文串。
 *
 * java
 *
 * public class Solution {
 *     public boolean canPermutePalindrome(String s) {
 *         int count = 0;
 *         for (char i = 0; i < 128 && count <= 1; i++) {
 *             int ct = 0;
 *             for (int j = 0; j < s.length(); j++) {
 *                 if (s.charAt(j) == i)
 *                     ct++;
 *             }
 *             count += ct % 2;
 *         }
 *         return count <= 1;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(128*|S|)O(128∗∣S∣)。我们枚举了 128 个字符，每次枚举需要遍历整个字符串，因此时间复杂度为 O(128*|S|)O(128∗∣S∣)。
 * 空间复杂度：O(1)O(1)。
 * 方法二：基于哈希的映射表（HashMap）
 * 我们可以使用映射表（map）帮助我们统计字符串中每个字符出现的次数。映射表中的键（key）存放字符，值（value）存放字符出现的次数。
 *
 * 我们对字符串进行遍历，并得到字符串对应的映射表。随后遍历映射表，如果发现超过一个字符出现了奇数次，那么字符串就不可以组成一个回文串。下面的幻灯片给出了整个过程。
 *
 *
 * 1 / 13
 *
 * java
 *
 * public class Solution {
 *  public boolean canPermutePalindrome(String s) {
 *      HashMap < Character, Integer > map = new HashMap < > ();
 *      for (int i = 0; i < s.length(); i++) {
 *          map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
 *      }
 *      int count = 0;
 *      for (char key: map.keySet()) {
 *          count += map.get(key) % 2;
 *      }
 *      return count <= 1;
 *  }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(|S|)O(∣S∣)。我们需要遍历整个字符串以及映射表，但映射表的大小一定不会大于字符串的长度，因此时间复杂度为 O(|S|)O(∣S∣)。
 * 空间复杂度：O(\min\{|S|, 128\})O(min{∣S∣,128})。在最坏的情况下，字符串中的每一个字符都不相同，但不同的字符数目最多只有 128 个。
 * 方法三：数组
 * 因为不同的字符数目最多只有 128 个，因此我们可以用长度为 128 的数组代替映射表。
 *
 * java
 *
 * public class Solution {
 *     public boolean canPermutePalindrome(String s) {
 *         int[] map = new int[128];
 *         for (int i = 0; i < s.length(); i++) {
 *             map[s.charAt(i)]++;
 *         }
 *         int count = 0;
 *         for (int key = 0; key < map.length && count <= 1; key++) {
 *             count += map[key] % 2;
 *         }
 *         return count <= 1;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(\max\{|S|, 128\})O(max{∣S∣,128})。我们需要遍历整个字符串以及数组，但数组的大小和字符串长度的关系未知，因此时间复杂度为 O(\max\{|S|, 128\})O(max{∣S∣,128})。
 * 空间复杂度：O(128)O(128)。数组的长度为 128。
 * 方法四：数组+单次循环
 * 方法三中有两次循环，第一次循环统计每个字符出现的次数，第二次循环统计出现奇数次的字符数目 \mathrm{count}count。我们可以把两个循环合二为一，只保留第一个循环，并在此循环中实时计算 \mathrm{count}count。
 *
 * 在对字符串进行遍历时，每一步更新了当前字符 \mathrm{c}c 出现的次数后，如果字符 \mathrm{c}c 出现了偶数次，我们就把 \mathrm{count}count 减一（这说明字符 \mathrm{c}c 在这一步前出现了奇数次），否则就把 \mathrm{count}count 加一。在遍历完整个字符串后，\mathrm{count}count 就表示字符串中出现奇数次的字符的数目。
 *
 * java
 *
 * public class Solution {
 *     public boolean canPermutePalindrome(String s) {
 *         int[] map = new int[128];
 *         int count = 0;
 *         for (int i = 0; i < s.length(); i++) {
 *             map[s.charAt(i)]++;
 *             if (map[s.charAt(i)] % 2 == 0)
 *                 count--;
 *             else
 *                 count++;
 *         }
 *         return count <= 1;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(|S|)O(∣S∣)。我们只需要遍历一次字符串。
 * 空间复杂度：O(128)O(128)。数组的长度为 128。
 * 方法五：集合
 * 方法四也可以使用集合而不是数组来实现。
 *
 * 集合里存放出现了奇数次的字符。在对字符串进行遍历时，如果字符 \mathrm{c}c 在集合中，就把它删除，否则就把它添加进集合中。在遍历完整个字符串后，集合的大小就表示字符串中出现奇数次的字符的数目。
 *
 * java
 *
 * public class Solution {
 *     public boolean canPermutePalindrome(String s) {
 *         Set < Character > set = new HashSet < > ();
 *         for (int i = 0; i < s.length(); i++) {
 *             if (!set.add(s.charAt(i)))
 *                 set.remove(s.charAt(i));
 *         }
 *         return set.size() <= 1;
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(|S|)O(∣S∣)。我们只需要遍历一次字符串。
 * 空间复杂度：O(\min\{|S|, 128\})O(min{∣S∣,128})。在最坏的情况下，字符串中的每一个字符都不相同，但不同的字符数目最多只有 128 个。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation/solution/hui-wen-pai-lie-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */