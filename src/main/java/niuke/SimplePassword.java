package niuke;

import java.util.Scanner;

/**
 * 简单密码
 *
 * 题目描述
 * 密码是我们生活中非常重要的东东，我们的那么一点不能说的秘密就全靠它了。哇哈哈. 接下来渊子要在密码之上再加一套密码，虽然简单但也安全。
 *
 *
 *
 * 假设渊子原来一个BBS上的密码为zvbo9441987,为了方便记忆，他通过一种算法把这个密码变换成YUANzhi1987，这个密码是他的名字和出生年份，怎么忘都忘不了，而且可以明目张胆地放在显眼的地方而不被别人知道真正的密码。
 *
 *
 *
 * 他是这么变换的，大家都知道手机上的字母： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0,就这么简单，渊子把密码中出现的小写字母都变成对应的数字，数字和其他的符号都不做变换，
 *
 *
 *
 * 声明：密码中没有空格，而密码中出现的大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
 *
 *
 * 输入描述:
 * 输入包括多个测试数据。输入是一个明文，密码长度不超过100个字符，输入直到文件结尾
 *
 * 输出描述:
 * 输出渊子真正的密文
 *
 * 示例1
 * 输入
 * YUANzhi1987
 * 输出
 * zvbo9441987
 */
public class SimplePassword {
    public static void main(String[] args) {
        // 65~90   A-Z
        // 97~122  a-z
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : line.toCharArray()) {
                char s = c;
                if (c >= 65 && c <= 90) {
                    s = (char)(c + 32 + 1);
                    if (c == 90) {
                        s = 'a';
                    }
                    stringBuilder.append(s);
                } else if (c == 'a' || c == 'b' || c == 'c'){
                    stringBuilder.append(2);
                } else if (c == 'd' || c == 'e' || c == 'f') {
                    stringBuilder.append(3);
                } else if (c == 'g' || c == 'h' || c == 'i') {
                    stringBuilder.append(4);
                } else if (c == 'j' || c == 'k' || c == 'l') {
                    stringBuilder.append(5);
                } else if (c == 'm' || c == 'n' || c == 'o') {
                    stringBuilder.append(6);
                } else if (c == 'p' || c == 'q' || c == 'r' || c == 's') {
                    stringBuilder.append(7);
                } else if (c == 't' || c == 'u' || c == 'v') {
                    stringBuilder.append(8);
                } else if (c == 'w' || c == 'x' || c == 'y' || c == 'z') {
                    stringBuilder.append(9);
                } else {
                    stringBuilder.append(s);
                }
            }
            System.out.println(stringBuilder.toString());
        }
        scanner.close();
    }
}
