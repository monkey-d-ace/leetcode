package solution;

/**
 * 1156. 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 *
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 * 示例 1：
 *
 * 输入：text = "ababa"
 * 输出：3
 * 示例 2：
 *
 * 输入：text = "aaabaaa"
 * 输出：6
 * 示例 3：
 *
 * 输入：text = "aaabbaaa"
 * 输出：4
 * 示例 4：
 *
 * 输入：text = "aaaaa"
 * 输出：5
 * 示例 5：
 *
 * 输入：text = "abcdef"
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 *
 */
public class SwapLongestRepeatCharacter {
    public int maxRepOpt1(String text) {
        if (text == null || text.length() < 1)
            return 0;
        int[] alpha = new int[26];
        for (int i = 0; i < text.length(); i++) {
            alpha[text.charAt(i) - 'a']++;
        }
        int res = 0;
        for (int j = 0; j < text.length(); j++) {
            int curCount = 1;
            int recoverJ = j;
            boolean firstDiffer = true;
            char ch = text.charAt(j);
            // 要么第一次遇到不同字符，要么跟自己一样的字符
            while (j < text.length() - 1 && (firstDiffer || ch == text.charAt(j + 1))) {
                if (ch != text.charAt(j + 1)) {
                    firstDiffer = false;
                    recoverJ = j;
                }
                j++;
                curCount++;
            }
            j = recoverJ;
            // 不能比字符串中该字符的次数更大
            curCount = Math.min(alpha[ch - 'a'], curCount);
            res = Math.max(curCount, res);
        }
        return res;
    }
}
