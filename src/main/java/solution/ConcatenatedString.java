package solution;

import java.util.List;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 *
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 * 通过次数9,129提交次数23,353
 * 在真实的面试中遇到过这道题？
 */
public class ConcatenatedString {
    public int getBitMask(String s) {
        int bitMask = 0;
        for (char c : s.toCharArray()) {
            int bit = 1 << (c - 'a');
            if ((bit & bitMask) != 0)
                return 0;
            bitMask |= bit;
        }
        return bitMask;
    }

    //此处函数的目的是为了返回串联字符的最大长度
    public int maxLengthDFS(List<String> arr, int start, int bitMask) {
        if (start == arr.size()) {
            return 0;
        }
        int ans = 0;
        for (int i = start; i < arr.size(); i++) {
            int bit = getBitMask(arr.get(i));
            if (bit == 0 || (bitMask & bit) != 0) continue;
            //一。当前子字符位掩码不为0（为0的话证明子字符里面有重复字符）
            //二。当前子字符位掩码与前面字符的位掩码与运算结果为0（如果结果不为0，那么说明与之前的字符串有重复)
            //满足上述两个条件才进入一下层递归。
            ans = Math.max(ans, maxLengthDFS(arr, i + 1, bitMask | bit) + arr.get(i).length());
        }
        return ans;
    }

    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        return maxLengthDFS(arr, 0, 0);
    }
}
