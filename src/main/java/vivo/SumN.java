package vivo;

import java.util.Scanner;

/**
 * 1,2,2,3,3,3,4,4,4,4,...
 * 求前n项和
 */
public class SumN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            //System.out.println(n * (n + 1) * (2 * n + 1) / 6);
            /*
            for (int i = 0; i < n; i++) {
                if ((1 + i) * i / 2 <= n && (1 + i + 1) * (i + 1) / 2 >= n) {
                    System.out.println((i * (i + 1) * (2 * i + 1) / 6) + (n - ((1 + i) * i / 2)) * (i + 1));
                    break;
                }
            }*/
            int m = (int)Math.sqrt(2 * n) - 1;
            int rest = n - (m + 1) * m / 2;
            int result = m * (m + 1) * (2 * m + 1) / 6;
            System.out.println(result + rest * (m + 1));
        }
        scanner.close();
    }
}
