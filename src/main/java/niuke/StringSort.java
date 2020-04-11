package niuke;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 字符串排序
 *
 * 题目描述
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 *
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 *
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 *
 *
 * 如，输入： By?e 输出： Be?y
 *
 *
 * 注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）
 *
 *
 * 输入描述:
 * 输入字符串
 * 输出描述:
 * 输出字符串
 * 示例1
 * 输入
 * A Famous Saying: Much Ado About Nothing (2012/8).
 * 输出
 * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class StringSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            char[] c = string.toCharArray();
            LinkedList<String> linkedList = new LinkedList<>();
            for (int i = 0; i < c.length; i++) {
                if ((c[i] >= 'a' && c[i] <= 'z') || c[i] >= 'A' && c[i] <= 'Z') {
                    linkedList.add(String.valueOf(c[i]));
                }
            }
            Collections.sort(linkedList, String.CASE_INSENSITIVE_ORDER);
            for (int i = 0, j = 0; i < c.length; i++) {
                if ((c[i] >= 'a' && c[i] <= 'z') || (c[i] >= 'A' && c[i] <= 'Z')) {
                    System.out.print(linkedList.get(j));
                    j += 1;
                } else {
                    System.out.print(c[i]);
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}
