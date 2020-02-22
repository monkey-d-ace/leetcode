package niuke;

import java.util.Scanner;

/**
 * 数字颠倒
 * 题目描述
 * 描述：
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 *
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 *
 * 输入描述:
 * 输入一个int整数
 *
 * 输出描述:
 * 将这个整数以字符串的形式逆序输出
 *
 * 示例1
 * 输入
 * 1516000
 * 输出
 * 0006151
 */
public class NumberReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            for (int i = s.length(); i > 0; i--) {
                sb.append(s.charAt(i - 1));
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
