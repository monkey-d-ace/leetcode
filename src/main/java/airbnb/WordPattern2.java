package airbnb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 291. 单词规律 II
 * 给你一种规律 pattern 和一个字符串 str，请你判断 str 是否遵循其相同的规律。
 *
 * 这里我们指的是 完全遵循，例如 pattern 里的每个字母和字符串 str 中每个 非空 单词之间，存在着 双射 的对应规律。双射 意味着映射双方一一对应，不会存在两个字符映射到同一个字符串，也不会存在一个字符分别映射到两个不同的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：pattern = "abab", s = "redblueredblue"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "red"
 * 'b' -> "blue"
 * 示例 2：
 *
 * 输入：pattern = "aaaa", s = "asdasdasdasd"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "asd"
 * 示例 3：
 *
 * 输入：pattern = "abab", s = "asdasdasdasd"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "a"
 * 'b' -> "sdasd"
 * 注意 'a' 和 'b' 不能同时映射到 "asd"，因为这里的映射是一种双射。
 * 示例 4：
 *
 * 输入：pattern = "aabb", s = "xyzabcxzyabc"
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= pattern.length <= 20
 * 0 <= s.length <= 50
 * pattern 和 s 由小写英文字母组成
 */
@Component
public class WordPattern2 {
    Map<Character, String> map = new HashMap<>();
    public boolean wordPatternMatch(String pattern, String str) {
        //边界条件，如果pattern读完了,字符串也正好读完就true，如果字符串没读完就false
        if (pattern.length() == 0) {
            return str.length() == 0;
        }
        char letter = pattern.charAt(0);
        //从1位开始尝试是否有映射，由于每个pattern至少得对应一个字符，所以如果字符串剩下的字符少于pattern剩下的字符数就可以停止循环了
        for (int i = 1; i <= str.length() - pattern.length() + 1; i++) {
            //mapStr是letter的映射，有则返回映射，没有则等于null
            String substr = str.substring(0, i);
            String mapStr = map.get(letter);
            //这个pattern有映射，并且等于这段字符；
            // 或者这段字符不是pattern的映射并且没有其他映射，就可以假设这个映射成立并继续尝试匹配剩下的字符
            if ((mapStr != null && substr.equals(mapStr)) || (mapStr == null && !map.containsValue(substr))) {
                //不管是否是正确答案，先放进map里面尝试
                map.put(letter, substr);
                //如果正好对了就返回true
                if (wordPatternMatch(pattern.substring(1), str.substring(i))) {
                    return true;
                } else if (mapStr == null) {
                    map.remove(letter);
                }
            }
        }
        //循环跑完都没有结果当然就返回false啦
        return false;
    }
}
/**
 * 解题思路
 * 1、题意理解
 * 如果字符串 pattern 和字符串 str 是遵循相同的规律，那么，pattern 的每一个字符一定对应 str 的某一个子串，而且是唯一对应。
 *
 * 例如：pattern = "abab", str = "redblueredblue"
 *
 * a -> read
 * b -> blue
 *
 * 因此，本题的目的就是找到这个对应关系，如果不存在对应关系，则不遵循相同的规律。
 *
 * 2、解题思路
 * 定义一个全局 HashMap<Character, String> 来存储映射关系，key 为 pattern 的字符，value 为 str 的子串。
 *
 * 一开始，map 中没有任何映射关系。
 *
 * 把 pattern = "abab" 的第一个字符 'a' 和 str = "redblueredblue" 的第一个字符 "r" 当成映射关系，put 到 map 中。现在 map 中有一个映射关系 ('a', "r")。
 *
 * 现在，问题变成查找 "bab" 和 "edblueredblue" 的映射关系，其中，'a' 必须映射 "r"。
 *
 * 很明显，这是一个回溯问题。
 *
 * 如果回溯到最后 pattern 没有字符了，且 str 也没有字符了，说明刚好映射完毕，返回 true。
 *
 * 如果 pattern 字符用完了，str 还有剩下的字符，说明有可能一开始的映射关系就是不对的，因此重新调整初始的映射关系，例如调整为 'a' -> "re" ，继续回溯 "bab" 和 "dblueredblue"。
 *
 * 因为 "abab" 一个字符至少对应 "redblueredblue 的一个字符，"abab" 长度为 4，因此，'a' 最多对应 "redblueredb"，留三个字符给 "aba"。
 *
 * 我们遍历回溯以下情况：
 *
 * 以 ('a' -> "r") 开始，查找 "bab" 和 "edblueredblue" 的映射关系；
 *
 * 以 ('a' -> "re") 开始，查找 "bab" 和 "dblueredblue" 的映射关系
 *
 * 以 ('a' -> "red") 开始，查找 "bab" 和 "blueredblue" 的映射关系
 *
 * ... ... ... ...
 *
 * 以 ('a' -> "redblueredb") 开始，查找 "bab" 和 "lue" 的映射关系
 *
 * 有可能中间某一个就直接返回 true，但是如果遍历完所有可能的开始，都没有返回 ture，说明也两个字符串不遵循相同的规律。
 *
 * 代码
 *
 * 作者：klb
 * 链接：https://leetcode-cn.com/problems/word-pattern-ii/solution/291-dan-ci-gui-lu-ii-by-klb/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */