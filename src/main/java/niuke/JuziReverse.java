package niuke;

import java.util.Scanner;

/**
 * 句子逆序
 * 题目描述
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 *
 * 接口说明
 * public String reverse(String sentence);
 * 输入描述:
 * 将一个英文语句以单词为单位逆序排放。
 *
 * 输出描述:
 * 得到逆序的句子
 *
 * 示例1
 * 输入
 * I am a boy
 * 输出
 * boy a am I
 */
public class JuziReverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] array = scanner.nextLine().split(" ");
            String[] result = new String[array.length];
            for (int i = array.length; i > 0; i--) {
                result[result.length - i] = array[i - 1];
            }
            System.out.println(String.join(" ", result));
        }
        scanner.close();
    }
}
