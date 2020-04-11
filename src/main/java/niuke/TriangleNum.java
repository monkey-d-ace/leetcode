package niuke;

import java.util.Scanner;

/**
 * 华为笔试题目：给定p周长，求所有满足a+b+c=p且为直角三角形的个数
 *
 * 思路：给出第一个边，在3~p/3，然后根据c=p-a-c, a^2+b^2=c^2,化简得到b=(p*(p-2*a))/(p-a)/2; 当b为正整数的时候，满足
 */
public class TriangleNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int p = scanner.nextInt();
            int ans = 0;
            for (int i = 0; i < p / 3; i++) {
                double j = p - (double) p * p / (2 * p - 2 * i);
                if (i < j && j - (int)j < Math.pow(10, -5)) {
                    ans += 1;
                }
            }
            System.out.println(ans);
        }
        scanner.close();
    }
}
