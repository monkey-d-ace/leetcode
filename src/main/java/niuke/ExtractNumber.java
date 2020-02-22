package niuke;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 提取不重复的整数
 * 题目描述
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 *
 * 输入描述:
 * 输入一个int型整数
 *
 * 输出描述:
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 *
 * 示例1
 * 输入
 * 9876673
 * 输出
 * 37689
 */
public class ExtractNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Character> set = new LinkedHashSet<>();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = s.length(); i > 0; i--) {
                if (set.add(s.charAt(i - 1))) {
                    stringBuilder.append(s.charAt(i - 1));
                }
            }
            System.out.println(stringBuilder.toString());
        }
        scanner.close();
    }
}
