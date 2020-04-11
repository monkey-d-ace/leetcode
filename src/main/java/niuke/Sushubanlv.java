package niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的N（N为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
 *
 * 输入:
 *
 * 有一个正偶数N（N≤100），表示待挑选的自然数的个数。后面给出具体的数字，范围为[2,30000]。
 *
 * 输出:
 *
 * 输出一个整数K，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 *
 *
 *
 * 输入描述:
 * 输入说明
 * 1 输入一个正偶数n
 * 2 输入n个整数
 *
 * 输出描述:
 * 求得的“最佳方案”组成“素数伴侣”的对数。
 *
 * 示例1
 * 输入
 * 4
 * 2 5 6 13
 * 输出
 * 2
 */
public class Sushubanlv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(scanner.nextInt());
            }
            int result = 0;
            List<Integer> restList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int number = list.get(i);
                if (!isSushu(number)) {
                    result += 1;
                } else {
                    restList.add(number);
                }
            }

            int i = 0, j = restList.size() - 1;
            while (i < j) {
                if (!isSushu(restList.get(i) + restList.get(j))) {
                    result += 1;
                    i++;
                    j--;
                } else {
                    i++;
                }
            }
            if (result > n / 2) {
                result = n / 2;
            }
            System.out.println(result);
        }
        scanner.close();
    }
    private static boolean isSushu(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
