package niuke;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 要开发一款教育类App，帮助幼儿在识数阶段做一百以内自然数[0,99]的加减法。
 * 屏幕上会显示“1”“2”“3”“4”“5”“6”“7”“8”“9”“0”“+”“-”“=”这些按钮，用户在按了若干按钮之后，如果按了“=”，则会把按“=”之前的字符作为一个算式，计算结果。
 * 中间结果或最后结果可以为负数。
 * 例子：
 * 输入：1+2-1+3+4
 * 输出：9
 *
 * 输入描述：
 * 输入为一个字符串，形如“23+86-6+37+24-8-13”.
 * 输入字符串中保证：
 * 1、不会包含除“1”“2”“3”“4”“5”“6”“7”“8”“9”“0”“+”“-”“=”之外的字符；
 * 2、长度不为零
 * 3、不以+ -开始
 * 4、不会出现连续两个或以上+ -
 * 5、+ - 不会相邻
 * 6、操作数范围：0-99
 * 其实这些就只是说明了输入的正确规则而已。
 *
 * 思路：1、输入的字符串要分割存在一个数组当中，然后用数组去遍历，其中两位数的需要注意一下怎么算
 * 2、其他的运算符要判断是“+”号还是“-”号
 */
public class Jiaoyu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int sum = 0;
            int temp = 0;
            int flag = 0;
            String string = scanner.nextLine();
            char[] chars = string.toCharArray();
            LinkedList<String> stack = new LinkedList<>();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] < '0' || chars[i] > '9') {
                    for (int j = flag; j < i; j++) {
                        temp += temp * 10 + (chars[j] - '0');
                    }
                    if (flag == 0) {
                        sum += temp;
                        temp = 0;
                    } else {
                        if (chars[flag - 1] == '+') {
                            sum += temp;
                            temp = 0;
                        } else if (chars[flag - 1] == '-') {
                            sum -= temp;
                            temp = 0;
                        }
                    }
                    flag = i + 1;
                }
            }
            for (int j = flag; j < chars.length; j++) {
                temp += temp * 10 + (chars[j] - '0');
                if (chars[flag - 1] == '+') {
                    sum += temp;
                    temp = 0;
                } else if (chars[flag - 1] == '-') {
                    sum -= temp;
                    temp = 0;
                }
            }
            System.out.println(sum);
        }
        scanner.close();
    }
}
