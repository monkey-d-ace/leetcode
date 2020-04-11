package niuke;

import java.util.Scanner;

/**
 * 输入一个字符串(不包含空格)，请寻找输入中包含的所有蛇形字符串。
 *
 * 蛇形字符串定义：
 *
 * 1、蛇形字符串由连续的字符对组成，其特点如下：
 *
 *       1.1 字符对定义：字符对由同一字母的大写和小写组成（前大后小），如Aa，Dd
 *
 *       1.2 蛇形字符串中包含的字符对，必须是连续字母，并按照字母顺序排序。如AaBbCc
 *
 * 2、从输入中寻找字符组成蛇形字符串（字符顺序不限），符合规则：
 *
 *      2.1 每次寻找必须是最长的蛇形字符串
 *
 *      2.2 使用过的字符不能重复使用
 *
 * 输入描述：
 *
 *       一个字符串（不含空格，字符串长度<= 5000）
 *
 * 输出描述：
 *
 *       1、所有包含的蛇形字符串，按照首字母升序排列（即A在Z前）
 *
 *       2、同一个首字母的情况，按照蛇形字符串的长度降序输出
 *
 *       3、如果没有找到输出Not Found
 */
public class SnakeString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.nextLine();

        }
        scanner.close();
    }
}
