package airbnb;

import org.springframework.stereotype.Component;

/**
 * 418. 屏幕可显示句子的数量
 * 给你一个 rows x cols 的屏幕和一个用 非空 的单词列表组成的句子，请你计算出给定句子可以在屏幕上完整显示的次数。
 *
 * 注意：
 *
 * 一个单词不能拆分成两行。
 * 单词在句子中的顺序必须保持不变。
 * 在一行中 的两个连续单词必须用一个空格符分隔。
 * 句子中的单词总量不会超过 100。
 * 每个单词的长度大于 0 且不会超过 10。
 * 1 ≤ rows, cols ≤ 20,000.
 *
 *
 * 示例 1：
 *
 * 输入：
 * rows = 2, cols = 8, 句子 sentence = ["hello", "world"]
 *
 * 输出：
 * 1
 *
 * 解释：
 * hello---
 * world---
 *
 * 字符 '-' 表示屏幕上的一个空白位置。
 *
 *
 * 示例 2：
 *
 * 输入：
 * rows = 3, cols = 6, 句子 sentence = ["a", "bcd", "e"]
 *
 * 输出：
 * 2
 *
 * 解释：
 * a-bcd-
 * e-a---
 * bcd-e-
 *
 * 字符 '-' 表示屏幕上的一个空白位置。
 *
 *
 * 示例 3：
 *
 * 输入：
 * rows = 4, cols = 5, 句子 sentence = ["I", "had", "apple", "pie"]
 *
 * 输出：
 * 1
 *
 * 解释：
 * I-had
 * apple
 * pie-I
 * had--
 *
 * 字符 '-' 表示屏幕上的一个空白位置。
 */
@Component
public class SentenceScreenFitting {
    int curorPostion = 0;
    int totalCount = 0;

    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (null == sentence || sentence.length == 0
                || 0 == rows || 0 == cols) {
            return 0;
        }

        int[] counts = new int[sentence.length];
        for(int i = 0 ; i < sentence.length; i++) {
            counts[i] = sentence[i].length();
        }

        /**根据 sentence 长度穷举例每种情况*/
        WordSituation[] cache = new WordSituation[sentence.length];
        for(int i = 0 ; i < sentence.length; i++) {
            int last = cols;
            int curorPostion = i;
            int totalCount = 0;
            WordSituation ws = new WordSituation();
            ws.start = i;
            while(counts[curorPostion] <= last) {
                /**包含空格*/
                last -= counts[curorPostion] + 1;
                curorPostion ++;
                if (curorPostion == counts.length) {
                    curorPostion = 0;
                    totalCount ++;
                }
            }
            ws.end = curorPostion;
            ws.count = totalCount;
            cache[i] = ws;
        }

        for (int i = 0; i < rows; i ++) {
            fillRow(cache);
        }
        return totalCount;
    }

    private void fillRow(WordSituation[] cache) {
        totalCount += cache[curorPostion].count;
        curorPostion = cache[curorPostion].end;
    }

    private static class WordSituation {
        int count;
        int start;
        int end;
    }

    public int wordsTyping2(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int totalLen = 0; //length of sentence
        for(int i=0; i<n; i++) {
            //A word cannot be split into two lines.
            if(sentence[i].length() > cols) return 0;
            totalLen += sentence[i].length();
        }
        if(totalLen > rows*cols) return 0;

        totalLen += n-1;
        if(cols == totalLen) return rows;
        //in case that cols is very long
        int newCol = cols%(totalLen+1);
        //cnts[i]: start with sentence[i], there are cnt[i] words in this row
        int[] cnts = new int[n];
        //count: the number of words in the area
        //end: length of all counted words, start from sentence[cur] to sentence[cur+count-1]
        //cur: the index of cnts that is calculating
        int count=0, end=0, cur=0;
        for(int i=0; i<2*n; i++) {
            end += sentence[i%n].length() + 1; // add word and space
            count++;
            //more than one row, calculate cnts[cur], seems like slide window
            while(end >= newCol && cur < n) {
                //whether the last word contains
                cnts[cur] = end <= newCol +1 ? count : count-1;
                //delete the first word [slide the left border of the window]
                end -= sentence[cur].length() + 1;
                count--;
                cur++;
                // System.out.println((cur-1) + "," + cnts[cur-1] +","+ count + "," + end);
            }
            if(cur >= n) break;
        }
        //the number of words in the screen
        int sum = 0;
        cur = 0; //the first word in each row
        for(int i=0; i<rows; i++) {
            sum += cnts[cur];
            cur = (cnts[cur] + cur)%n;
            //  System.out.println(cur + "," + sum);
        }
        return rows*(cols/(totalLen+1)) + sum/n;
    }
}
/**
 * 1）计算以每个单词开始时，每一行可以装下多少个单词。使用滑动窗口的思想
 * 2）考虑cols特别长，而sentence比较短的情况：每一行都会出现很多句子，可以先去掉每行整句话所占用的长度，只填充剩下的屏幕。即使用newCol计算。
 * 3）计算屏幕中，可以装下的单词总数，即sum。
 * 4）结果 = sum/n + rows*(每一行整句话的个数)
 *
 * 作者：miaomiao-2
 * 链接：https://leetcode-cn.com/problems/sentence-screen-fitting/solution/java-shuang-bai-hua-dong-chuang-kou-jian-zhi-by-mi/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */