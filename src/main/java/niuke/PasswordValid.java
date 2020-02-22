package niuke;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 密码验证合格程序
 * 题目描述
 * 密码要求:
 *
 *
 *
 *
 *
 *
 *
 * 1.长度超过8位
 *
 *
 *
 *
 *
 *
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 *
 *
 *
 *
 *
 *
 * 3.不能有相同长度超2的子串重复
 *
 *
 *
 *
 *
 *
 *
 * 说明:长度超过2的子串
 *
 *
 * 输入描述:
 * 一组或多组长度超过2的子符串。每组占一行
 *
 * 输出描述:
 * 如果符合要求输出：OK，否则输出NG
 *
 * 示例1
 * 输入
 * 021Abc9000
 * 021Abc9Abc1
 * 021ABC9000
 * 021$bc9000
 * 输出
 * OK
 * NG
 * NG
 * OK
 */
public class PasswordValid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String password = sc.nextLine();
            if (verifyPassword(password)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
        sc.close();
    }

    /**
     * 验证密码合法性
     *
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password) {
        // 长度超过8位
        if (password == null || password.length() <= 8) {
            return false;
        }

        if (checkCharTypes(password) && checkRepeatSubstring(password)) {
            return true;
        }
        return false;
    }

    /**
     * 包括大小写字母、数字、其它符号,以上四种至少三种
     *
     * @param password
     * @return
     */
    public static boolean checkCharTypes(String password) {
        int upperCase = 0, lowerCase = 0, digit = 0, other = 0;

        for (Character ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCase = 1;
            } else if (Character.isLowerCase(ch)) {
                lowerCase = 1;
            } else if (Character.isDigit(ch)) {
                digit = 1;
            } else {
                other = 1;
            }
        }
        // for (Character ch : password.toCharArray()) {
        //     if (ch >= 'A' && ch <= 'Z') {
        //         upperCase = 1;
        //     } else if (ch >= 'a' && ch <= 'z') {
        //         lowerCase = 1;
        //     } else if (ch >= '0' && ch <= '9') {
        //         digit = 1;
        //     } else {
        //         other = 1;
        //     }
        // }

        if (upperCase + lowerCase + digit + other >= 3) {
            return true;
        }
        return false;
    }

    /**
     * 不能有相同长度超2的子串重复
     *
     * @param password
     * @return
     */
    public static boolean checkRepeatSubstring(String password) {
        for (int i = 0; i < password.length() - 3; i++) {
            String s = password.substring(i, i + 3);
            String tempStr = password.substring(i + 3, password.length());
            if (tempStr.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
