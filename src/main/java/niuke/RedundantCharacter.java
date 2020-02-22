package niuke;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 重复字符排序
 * 题目描述：找出输入字符串中的重复字符，再根据ASCII码把重复的字符从小到大排序。
 *
 * 例如：输入ABCABCdd，输出ABCd。
 *
 * 解答：计数排序，没有什么难度（计数排序是华为机试题出现次数最多的排序方法，没有之一）：
 */
public class RedundantCharacter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();

            Map<Character, Integer> map = new TreeMap<>(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    return o1 - o2;
                }
            });
            for (char c : str.toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 1) {
                    stringBuilder.append(entry.getKey());
                }
            }
            System.out.println(stringBuilder.toString());
        }
        scanner.close();
    }
}
