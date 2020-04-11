package niuke;

import java.util.*;

/**
 * 题目描述
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 *
 * 处理：
 *
 *
 * 1、 记录最多8条错误记录，循环记录（或者说最后只输出最后出现的八条错误记录），对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
 *
 *
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 *
 *
 * 3、 输入的文件可能带路径，记录文件名称不能带路径。
 *
 *
 * 输入描述:
 * 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
 *
 * 输出描述:
 * 将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开，如：
 *
 * 示例1
 * 输入
 * 复制
 * E:\V1R2\product\fpgadrive.c   1325
 * 输出
 * 复制
 * fpgadrive.c 1325 1
 */
public class SimpleErrorRecord2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>();
        while (sc.hasNext()) {
            String errorLine = sc.nextLine();
            String[] array = errorLine.split(" ");
            String fileName = !array[0].contains("\\") ? array[0] : array[0].substring(array[0].lastIndexOf("\\") + 1);
            String mapKey = fileName + " " + array[1];
            if (!map.containsKey(mapKey)) {
                map.put(mapKey, 1);
            } else {
                map.put(mapKey, map.get(mapKey) + 1);
            }
        }
        sc.close();
        int count = 0;
        for (String tmp : map.keySet()) {
            count++;
            if (count > (map.keySet().size() - 8)) {
                String[] s = tmp.split(" ");
                String name = s[0];
                if (s[0].length() > 16) {
                    name = s[0].substring(s[0].length() - 16);
                }
                System.out.println(name + " " + s[1] + " " + map.get(tmp));
            }
        }
    }
}
