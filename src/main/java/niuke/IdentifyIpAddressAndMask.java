package niuke;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 识别有效的IP地址和掩码并进行分类统计
 * 题目描述
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 *
 * 所有的IP地址划分为 A,B,C,D,E五类
 *
 * A类地址1.0.0.0~126.255.255.255;
 *
 * B类地址128.0.0.0~191.255.255.255;
 *
 * C类地址192.0.0.0~223.255.255.255;
 *
 * D类地址224.0.0.0~239.255.255.255；
 *
 * E类地址240.0.0.0~255.255.255.255
 *
 *
 * 私网IP范围是：
 *
 * 10.0.0.0～10.255.255.255
 *
 * 172.16.0.0～172.31.255.255
 *
 * 192.168.0.0～192.168.255.255
 *
 *
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * 注意二进制下全是1或者全是0均为非法
 *
 * 注意：
 * 1. 类似于【0.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 *
 * 输入描述:
 * 多行字符串。每行一个IP地址和掩码，用~隔开。
 *
 * 输出描述:
 * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
 *
 * 示例1
 * 输入
 * 10.70.44.68~255.254.255.0
 * 1.0.0.1~255.0.0.0
 * 192.168.0.2~255.255.255.0
 * 19..0.~255.255.255.0
 * 输出
 * 1 0 1 0 0 2 1
 */
public class IdentifyIpAddressAndMask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] array = scanner.nextLine().split("~");
            int[] countIp = new int[5];
            int countErrorIpOrMask = 0;
            int countPrivateIp = 0;
            Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
            Matcher matcher = pattern.matcher(array[0]);
            if (matcher.matches()) {
                countIp[0]++;
                //先判断是否为私有地址
                int group1 = Integer.parseInt(matcher.group(1));
                int group2 = Integer.parseInt(matcher.group(2));
                int group3 = Integer.parseInt(matcher.group(3));
                int group4 = Integer.parseInt(matcher.group(4));
                if ((group3 >= 0 && group3 <= 255 && group4 >= 0 && group4 <= 255) && ((group1 == 10 && group2 >= 0 && group2 <= 255) || (group1 == 172 && group2 >= 16 && group2 <= 31) || (group1 == 192 && group2 == 168))) {
                    countPrivateIp++;
                }
                if (!((group1 >= 0 && group1 <= 126) || (group1 >= 128 && group1 <= 255)) || !(group2 >= 0)) {

                }
            }
        }
        scanner.close();
    }
}
