package airbnb;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 * 示例 1:
 *
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 *
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n*k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

        int max_len = 1;

        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);

            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
/**
 * 方法 2：滑动窗口 + 有序字典
 * 如何达到 O(N)O(N) 时间复杂度
 *
 * 方法 1 使用了标准的哈希表，不能够保证 O(N)O(N) 的复杂度。
 *
 * 为了达到 O(N)O(N) 的效率，我们可以使用一种数据结构，保证以下四种操作都可以在 O(1)O(1) 时间完成：
 *
 * 插入键
 * 获取键 / 检查键是否存在
 * 删除键
 * 返回最先 / 最后插入的键值对
 * 前三个操作通过标准的哈希表就可以实现，第四个操作使用链表可以实现。
 *
 * 使用有序字典结构，可以同时支持哈希表和链表操作，在 Python 中这个结构叫做 OrderedDict， Java 中为 LinkedHashMap。
 *
 * 有序字典在面试中很常见，相关例题可以查看LRU缓存机制问题。
 *
 * 算法
 *
 * 使用有序字典代替标准哈希表解决方法 1：
 *
 * 如果字符串为空或者 k 是零的话返回 0。
 * 设置指针为字符串开头 left = 0 和 right = 0，初始化最大子串长度 max_len = 1。
 * 当 right 指针小于 N 时：
 * 如果当前字符 s[right] 已经在有序字典中 hashmap 中，删除它，以保证 hashmap 的第一个元素是滑动窗口的最左侧元素。
 * 将 s[right] 加入有序字典，并右移 right 指针。
 * 如果有序字典 hashmap 包含 k + 1 个不同字符，在哈希表中移去最左出现的字符，移动 left 指针使得滑动窗口只包含 k 个不同字符。
 * 更新 max_len。
 *
 *
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N) 因为有序字典的所有操作 insert/get/delete/popitem
 * (put/containsKey/remove) 都在常数时间内完成。
 * 空间复杂度：O(k)O(k)，有序字典的空间开销，最多保存 k + 1 个元素。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/zhi-shao-bao-han-k-ge-bu-tong-zi-fu-de-zui-chang-z/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */