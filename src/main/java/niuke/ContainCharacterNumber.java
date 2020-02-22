package niuke;

import java.util.Scanner;

/**
 * 题目描述
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 *
 * 输入描述:
 * 第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
 *
 * 输出描述:
 * 输出输入字符串中含有该字符的个数。
 *
 * 示例1
 * 输入
 * ABCDEF
 * A
 * 输出
 * 1
 */
public class ContainCharacterNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String c = sc.next();
            int result = 0;
            for (int i = 0; i < str.length(); i++) {
                if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(c.charAt(0))) {
                    result++;
                }
            }
            System.out.println(result);
        }
        sc.close();
    }
}
