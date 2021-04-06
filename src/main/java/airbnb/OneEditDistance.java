package airbnb;

import org.springframework.stereotype.Component;

/**
 * 161. 相隔为 1 的编辑距离
 * 给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
 *
 * 注意：
 *
 * 满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入一个字符得到 t
 * 从 s 中删除一个字符得到 t
 * 在 s 中替换一个字符得到 t
 * 示例 1：
 *
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 *
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 * 示例 3:
 *
 * 输入: s = "1203", t = "1213"
 * 输出: true
 * 解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
 * 通过次数5,738提交次数17,213
 */
@Component
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt)
            return isOneEditDistance(t, s);

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++)
            if (s.charAt(i) != t.charAt(i))
                // if strings have the same length
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // if strings have different lengths
                else
                    return s.substring(i).equals(t.substring(i + 1));

        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }
}
/**
 * 遍历一遍的算法：
 * 想法
 *
 * 首先，我们确认两个字符串的长度差得不会太远。如果长度差大于等于 2 个字符，那么 s 和 t 肯定不是编辑距离为 1 的字符串。
 *
 *
 *
 * 接下来，我们假设 s 总是比 t 短或者长度相等。否则，调用函数 isOneEditDistance(t, s) 来将两个字符串交换。
 *
 * 现在，遍历字符串一遍并找到第一个不同的字符。
 *
 * 如果前 len(s) 个字符没有差别，那么只有两种可能：
 *
 * 两个字符串是相同的
 *
 * 两个字符串的编辑距离相隔为 1
 *
 *
 *
 * 如果存在不同的字符 s[i] != t[i]：
 *
 * 如果两个字符串的长度相等，所有 接下来的字符都应该相等，否则编辑距离不为 1 。这种情况下，我们需要比较 s 和 t 从第 i + 1 个字符开始的子串是否相等。
 *
 * 如果 t 比 s 多一个字符， t[i] 应该是 t 串多出来的那个字符。这种情况下，我们需要比较 s 串从第 i 个字符开始的子串和 t 串从 i + 1 开始的子串是否相等。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/one-edit-distance/solution/xiang-ge-wei-1-de-bian-ji-ju-chi-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */