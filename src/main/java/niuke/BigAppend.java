package niuke;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * [编程题]大数加法
 * 时间限制：C/C++ 1秒，其他语言2秒
 *
 * 空间限制：C/C++ 256M，其他语言512M
 *
 * 以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和。
 *
 * 输入描述:
 * 输入两行，表示两个数字a和b，-109 <= a , b <= 109  ，用双引号括起。
 *
 * 输出描述:
 * 输出a+b的值，用双引号括起。
 *
 * 输入例子1:
 * "-26"
 * "100"
 *
 * 输出例子1:
 * "74"
 */
public class BigAppend {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            a = a.substring(1, a.lastIndexOf("\""));
            b = b.substring(1, b.lastIndexOf("\""));
            BigDecimal bigDecimal_a = new BigDecimal(a);
            BigDecimal bigDecimal_b = new BigDecimal(b);
            System.out.println("\"" + bigDecimal_a.add(bigDecimal_b).toString() + "\"");
        }
        scanner.close();
    }
}
