package airbnb;

import org.springframework.stereotype.Component;

import java.util.Stack;

/**
 * 772. 基本计算器 III
 * 实现一个基本的计算器来计算简单的表达式字符串。
 *
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。整数除法需要 向下截断 。
 *
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围为 [-231, 231 - 1] 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "1+1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = "6-4/2"
 * 输出：4
 * 示例 3：
 *
 * 输入：s = "2*(5+5*2)/3+(6/2+8)"
 * 输出：21
 * 示例 4：
 *
 * 输入：s = "(2+6*3+5-(3*14/7+2)*5)+3"
 * 输出：-12
 * 示例 5：
 *
 * 输入：s = "0"
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= s <= 104
 * s 由整数、'+'、'-'、'*'、'/'、'(' 和 ')' 组成
 * s 是一个 有效的 表达式
 *
 *
 * 进阶：你可以在不使用内置库函数的情况下解决此问题吗？
 */
@Component
public class BasicCalculator3 {
    public int calculate(String s) {
        return dfs(s, 0)[0];
    }

    private int[] dfs(String s, int index) {
        Stack<Integer> st = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int[] root = dfs(s, ++i);
                num = root[0];
                i = root[1];
            }
            if (i == s.length() - 1 || (!Character.isDigit(c) && c != ' ')) {
                switch (sign) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-num);
                        break;
                    case '*':
                        st.push(num * st.pop());
                        break;
                    case '/':
                        st.push(st.pop() / num);
                        break;
                    default:
                        break;
                }
                sign = c;
                num = 0;
            }
            if (c == ')') {
                return new int[]{sum(st), i};
            }
        }
        return new int[]{sum(st), s.length() - 1};
    }

    private int sum(Stack<Integer> st) {
        int res = 0;
        while (!st.isEmpty()) {
            res += st.pop();
        }
        return res;
    }
}
