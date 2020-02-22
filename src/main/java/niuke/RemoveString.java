package niuke;

import java.util.*;

/**
 * 删除字符串中出现最少的字符
 * 题目描述
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 输入描述:
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 *
 * 输出描述:
 * 删除字符串中出现次数最少的字符后的字符串。
 *
 * 示例1
 * 输入
 * abcdd
 * 输出
 * dd
 */
public class RemoveString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            List<String> stringList = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            String[] array = scanner.nextLine().split("");
            for (int i = 0; i < array.length; i++) {
                if (map.containsKey(array[i])) {
                    map.put(array[i], map.get(array[i]) + 1);
                } else {
                    map.put(array[i], 1);
                }
                stringList.add(array[i]);
            }

            int min = 21;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int v = entry.getValue();
                if (v < min) {
                    min = v;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < stringList.size(); i++) {
                if (!(map.get(stringList.get(i)) == min)) {
                    stringBuilder.append(stringList.get(i));
                }
            }
            System.out.println(stringBuilder.toString());
        }
        scanner.close();
    }
}
