package niuke;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Baowenzhuanhuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int length = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            int trans = 0;
            for (int i = 0; i < length - 1; i++) {
                String str = sc.next();
                if (str.equals("A")) {
                    sb.append(12);
                    sb.append(" ");
                    sb.append(34);
                    trans++;
                } else if (str.equals("B")) {
                    sb.append("AB");
                    sb.append(" ");
                    sb.append("CD");
                    trans++;
                } else {
                    sb.append(Integer.toHexString(Integer.parseInt(str)));
                    sb.append(" ");
                }
            }
            sb.insert(0, Integer.toHexString(length + trans));
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
