package niuke;

import java.util.Scanner;

/**
 * 坐标移动
 *
 * 题目描述
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 *
 *
 *
 * 输入：
 *
 *
 *
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 *
 *
 *
 * 坐标之间以;分隔。
 *
 *
 *
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 *
 *
 *
 * 下面是一个简单的例子 如：
 *
 *
 *
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 *
 *
 *
 * 处理过程：
 *
 *
 *
 * 起点（0,0）
 *
 *
 *
 * +   A10   =  （-10,0）
 *
 *
 *
 * +   S20   =  (-10,-20)
 *
 *
 *
 * +   W10  =  (-10,-10)
 *
 *
 *
 * +   D30  =  (20,-10)
 *
 *
 *
 * +   x    =  无效
 *
 *
 *
 * +   A1A   =  无效
 *
 *
 *
 * +   B10A11   =  无效
 *
 *
 *
 * +  一个空 不影响
 *
 *
 *
 * +   A10  =  (10,-10)
 *
 *
 *
 *
 *
 *
 *
 * 结果 （10， -10）
 *
 *
 * 输入描述:
 * 一行字符串
 *
 * 输出描述:
 * 最终坐标，以,分隔
 *
 * 示例1
 * 输入
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * 输出
 * 10,-10
 */
public class DirectMove {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int x = 0;
            int y = 0;
            String line = scanner.nextLine();
            String[] array = line.split(";");
            for (String s : array) {
                try {
                    int num = Integer.parseInt(s.substring(1));
                    if (s.charAt(0) == 'A') {
                        x -= num;
                    } else if (s.charAt(0) == 'S') {
                        y -= num;
                    } else if (s.charAt(0) == 'W') {
                        y += num;
                    } else if (s.charAt(0) == 'D') {
                        x += num;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            System.out.println(x + "," + y);
        }
        scanner.close();
    }
}
