package algorithm;

import java.util.List;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        String s = "10.10.10.10";
        System.out.println(new Main().isIP(s));
        s = "abc.abc..";
        System.out.println(new Main().isIP(s));
        s = "255.255.255.700";
        System.out.println(new Main().isIP(s));

        s = "aaa.245.457";
        System.out.println(new Main().isIP(s));
        String emp = "";
        System.out.println(new Main().isIP(emp));
    }

    public boolean isIP(String s) {
        String[] str = s.split("\\.");
        if (str.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            if (str[i].length() < 0 || !isNumber(str[i])) {
                return false;
            }

            if (Integer.parseInt(str[i]) <= 0 || Integer.parseInt(str[i]) > 255) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }
}
