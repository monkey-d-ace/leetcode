package niuke;

import java.util.Scanner;

/**
 * 如果报文中出现0x0A，转义成2个字节0x12 0x34，如果出现0x0B的话，转义成0xAB 0xCD，其他维持不变
 * 输入报文为16进制（加红是因为这边设了一个坑。。最后AC20%也是这个原因）输入的报文第一个字节是报文长度，报文长度这个字节也属于报文的一个部分，但是不参与转换的，输入的每个报文是用空格隔开的
 * 要求：输出的报文的是大写的16进制，前面不用加前缀0x；也用空格隔开；
 * 测试用例:
 * 输入
 * 8 1 2 3 4 5 6 A
 * 输出
 * 9 1 2 3 4 5 6 12 34
 */
public class Baowenzhuanhuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] array = sc.nextLine().split(" ");
            int transNum = Integer.parseInt(array[0], 16);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("A")) {
                    stringBuilder.append("12");
                    stringBuilder.append(" ");
                    stringBuilder.append("34");
                    stringBuilder.append(" ");
                    transNum++;
                } else if (array[i].equals("B")) {
                    stringBuilder.append("AB");
                    stringBuilder.append(" ");
                    stringBuilder.append("CD");
                    stringBuilder.append(" ");
                    transNum++;
                } else {
                    stringBuilder.append(array[i]);
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.insert(0, Integer.toHexString(transNum).toUpperCase() + " ");
            System.out.println(stringBuilder.toString().trim());
        }
        sc.close();
    }
}
