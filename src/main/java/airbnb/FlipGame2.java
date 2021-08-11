package airbnb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 294. 翻转游戏 II
 * 你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：
 *
 * 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。
 *
 * 请你写出一个函数来判定起始玩家 是否存在必胜的方案 ：如果存在，返回 true ；否则，返回 false 。
 *
 *
 * 示例 1：
 *
 * 输入：currentState = "++++"
 * 输出：true
 * 解释：起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。
 * 示例 2：
 *
 * 输入：currentState = "+"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= currentState.length <= 60
 * currentState[i] 不是 '+' 就是 '-'
 *
 *
 * 进阶：请推导你算法的时间复杂度。
 */
@Component
public class FlipGame2 {
    public boolean canWin(String s) {
        List<Integer> gList = new ArrayList<>();
        int n = s.length();
        int i = 0, j = 0;
        int max = 0;
        while (i < n) {
            if (s.charAt(i) == '-') i++;
            else {
                for (j = i; j < n && s.charAt(j) == '+'; j++);
                gList.add(j - i);
                if (j - i > max) max = j - i;
                i = j + 1;
            }
        }
        if (max <= 1) return false;
        int[] g = new int[max + 1];
        g[0] = 0; g[1] = 0;
        for (int k = 2; k < max + 1; k++) {
            BitMap bm = new BitMap();
            for (int l = 0; l <= (k - 2) / 2; l++) {
                bm.set(g[l] ^ g[k - 2 - l]);
            }
            for (int l = 0; l < k; l++) {
                if (!bm.contains(l)) { g[k] = l; break; }
            }
        }
        int result = 0;
        while (!gList.isEmpty()) {
            result = result ^ g[gList.get(0)];
            gList.remove(0);
        }
        return result != 0;
    }

    class BitMap {
        char[] M;
        int N;

        BitMap(int n) {
            N = (n + 8) / 8;
            M = new char[N];
        }

        BitMap() {
            N = 1;
            M = new char[N];
        }

        private void expand(int k) {
            if ((k + 8) / 8 < N) return;
            int oldN = N;
            N = (k + 8) / 8 * 2;
            char[] newM = new char[N];
            for (int i = 0; i < oldN; i++) {
                newM[i] = M[i];
            }
            M = newM;
        }

        public boolean contains(int k) {
            expand(k);
            return (M[k >> 3] & (0x80 >> (k & 0x07))) != 0;
        }

        public void set(int k) {
            expand(k);
            M[k >> 3] = (char) (M[k >> 3] | (0x80 >> (k & 0x07)));
        }

        public void delete(int k) {
            expand(k);
            M[k >> 3] = (char) (M[k >> 3] & (~(0x80 >> (k & 0x07))));
        }
    }
}
/**
 * 第一反应是动态规划，想了一天没想出来。。后来看到一亩三分地里的SG函数的做法，应用后就能用动态规划自底向上里，贴上来供大家参考：https://www.1point3acres.com/bbs/thread-137953-1-1.html
 * 证明可见这个知乎回答：10170 Sprague-Grundy定理是怎么想出来的 - 王赟 Maigo的文章 - 知乎
 * https://zhuanlan.zhihu.com/p/20611132
 * 附上自己的代码：
 *
 * 作者：liu-xing-0923
 * 链接：https://leetcode-cn.com/problems/flip-game-ii/solution/java-sghan-shu-zuo-fa-shuang-100-by-liu-xing-3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */