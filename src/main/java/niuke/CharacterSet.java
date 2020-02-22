package niuke;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/784efd40ed8e465a84821c8f3970b7b5
 * 来源：牛客网
 *
 * [编程题]字符集合
 * 热度指数：39483时间限制：C/C++ 1秒，其他语言2秒空间限制：C/C++ 32M，其他语言64M
 * 算法知识视频讲解
 * 输入一个字符串，求出该字符串包含的字符集合
 *
 * 输入描述:
 * 每组数据输入一个字符串，字符串最大长度为100，且只包含字母，不可能为空串，区分大小写。
 *
 *
 * 输出描述:
 * 每组数据一行，按字符串原有的字符顺序，输出字符集合，即重复出现并靠后的字母不输出。
 * 示例1
 * 输入
 * abcqweracb
 * 输出
 * abcqwer
 */
public class CharacterSet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            char[] c = next.toCharArray();
            StringBuilder sb = new StringBuilder();
            Set<Character> set = new LinkedHashSet<>();
            for (char cur : c) {
                if (set.add(cur))
                    sb.append(cur);
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
