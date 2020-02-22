package niuke;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shuzifenjie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            if (number == 0) {
                System.out.println("end");
            }
            Map<Integer, Integer> map = new HashMap<>();
            int sushu = 2;
            while (sushu <= number) {
                if (isSushu(sushu) && isSushu(number - sushu)) {
                    if (!(map.containsKey(sushu) || map.containsKey(number - sushu))) {
                        map.put(sushu, number - sushu);
                    }
                }
                sushu++;
            }
            System.out.println(map.entrySet().size());
        }
        scanner.close();
    }

    public static boolean isSushu(int number) {
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
