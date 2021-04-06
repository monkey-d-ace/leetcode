package airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<String> row = new ArrayList<>();

        int currentLen = 0;
        for (int i = 0; i < words.length; ) {
            if (currentLen == 0 && currentLen + words[i].length() <= maxWidth || currentLen > 0 && currentLen + 1 + words[i].length() <= maxWidth) {
                row.add(words[i]);
                if (currentLen == 0) {
                    currentLen += words[i].length();
                } else {
                    currentLen += 1 + words[i].length();
                }
                i++;
            } else {
                int sub = maxWidth - currentLen + row.size() - 1;
                if (row.size() == 1) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(row.get(0)).append(getStringBlank(maxWidth - currentLen));
                    ans.add(temp.toString());
                } else {
                    int averageBlank = sub / (row.size() - 1);
                    int missing = sub - averageBlank * (row.size() - 1);
                    StringBuilder addStr = new StringBuilder();
                    addStr.append(row.get(0));
                    int k = 1;
                    for (int j = 0; j < missing; j++) {
                        addStr.append(getStringBlank(averageBlank + 1)).append(row.get(k++));
                    }

                    for (; k < row.size(); k++) {
                        addStr.append(getStringBlank(averageBlank)).append(row.get(k));
                    }
                    ans.add(addStr.toString());
                }
                row = new ArrayList<>();
                currentLen = 0;
            }

        }

        StringBuilder temp = new StringBuilder();
        temp.append(row.get(0));
        for (int i = 1; i < row.size(); i++) {
            temp.append(" ").append(row.get(i));
        }
        temp.append(getStringBlank(maxWidth - currentLen));
        ans.add(temp.toString());
        return ans;
    }

    public String getStringBlank(int n) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append(" ");
        }
        return str.toString();
    }
}
