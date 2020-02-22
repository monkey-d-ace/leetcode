package niuke;

import java.util.Scanner;

/**
 * 字符串反转
 * 题目描述
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 *
 * 输入描述:
 * 输入N个字符
 *
 * 输出描述:
 * 输出该字符串反转后的字符串
 *
 * 示例1
 * 输入
 * abcd
 * 输出
 * dcba
 */
public class StringReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            StringBuilder sb = new StringBuilder();
            String s = sc.nextLine();
            for (int i = s.length(); i > 0; i--) {
                sb.append(s.charAt(i - 1));
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
