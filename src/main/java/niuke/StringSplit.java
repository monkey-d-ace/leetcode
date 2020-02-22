package niuke;

import java.util.Scanner;

/**
 *
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 *
 * 输出描述:
 * 输出到长度为8的新字符串数组
 *
 * 示例1
 * 输入
 * abc
 * 123456789
 * 输出
 * abc00000
 * 12345678
 * 90000000
 */
public class StringSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.equals("")) {
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s);
            int rev = s.length() % 8;
            if (rev != 0) {
                for (int i = 0; i < (8 - rev); i++) {
                    stringBuilder.append(0);
                }
            }
            int i = 0;
            while (i < stringBuilder.length()) {
                System.out.println(stringBuilder.substring(i, i+8));
                i += 8;
            }
        }
        scanner.close();
    }
}
