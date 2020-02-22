package niuke;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/8f3df50d2b9043208c5eed283d1d4da6
 * 来源：牛客网
 *
 * [编程题]进制转换
 * 热度指数：268954时间限制：C/C++ 1秒，其他语言2秒空间限制：C/C++ 32M，其他语言64M
 * 算法知识视频讲解
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 *
 *
 * 输入描述:
 * 输入一个十六进制的数值字符串。
 *
 *
 *
 * 输出描述:
 * 输出该数值的十进制字符串。
 *
 * 示例1
 * 输入
 * 0xA
 * 输出
 * 10
 */
public class BitChange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(Integer.decode(sc.next()));
        }
        sc.close();
    }
}
