package solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 722. 删除注释
 * 给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第i行源码。 这表示每行源码由\n分隔。
 *
 * 在 C++ 中有两种注释风格，行内注释和块注释。
 *
 * 字符串// 表示行注释，表示//和其右侧的其余字符应该被忽略。
 *
 * 字符串\/* 表示一个块注释，它表示直到*\/的下一个（非重叠）出现的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串\/*\/并没有结束块注释，因为注释的结尾与开头相重叠。
 *
 * 第一个有效注释优先于其他注释：如果字符串//出现在块注释中会被忽略。 同样，如果字符串/*出现在行或块注释中也会被忽略。
 *
 * 如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。
 *
 * 样例中没有控制字符，单引号或双引号字符。比如，source = "string s = "\/* Not a comment. *\/";"不会出现在测试样例里。（此外，没有其他内容（如定义或宏）会干扰注释。）
 *
 * 我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的\/*总是开始新的注释。
 *
 * 最后，隐式换行符可以通过块注释删除。 有关详细信息，请参阅下面的示例。
 *
 * 从源代码中删除注释后，需要以相同的格式返回源代码。
 *
 */
public class RemoveComments {
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (String line : source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) newline = new StringBuilder();
            while (i < line.length()) {
                if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
                    break;
                } else if (!inBlock) {
                    newline.append(chars[i]);
                }
                i++;
            }
            if (!inBlock && newline.length() > 0) {
                ans.add(newline.toString());
            }
        }
        return ans;
    }
}
/**
 * 复杂度分析
 *
 * 时间复杂度：O(S)。SS 指的是源代码的长度。
 * 空间复杂度：O(S)，将源代码记录到 ans 中所使用的空间。
 *
 * 算法：
 *
 * 我们需要逐行分析源代码。有两种情况，要么在一个注释内或者不在。
 * 如果我们遇到注释块符号，而我们不在注释中，那么我们将跳过接下来的两个字符，并将状态更改为在注释中。
 * 如果我们遇到注释块符号并且我们在注释中，那么我们将跳过接下来的两个字符并将状态更改为不在注释中。
 * 如果我们遇到一个行注释且我们不在注释中，那么我们将忽略该行的其余部分。
 * 如果我们不在注释中（并且它不是注释的开头），我们将记录所遇到的字符。
 * 在每行的末尾，如果我们不在注释中，我们将记录该行。
 *
 */