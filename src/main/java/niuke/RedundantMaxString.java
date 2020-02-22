package niuke;

import java.util.*;

/**
 * 重复字符最长串
 * 题目描述：给定一串字符，里面有些字符有连续出现的特点，请寻找这些连续出现字符中最长的串，如果最长的串有多个，请输出字符ASCII码最小的那一串。
 *
 * 例如：输入aaabbbbbcccccccczzzzzzzz，输出cccccccc。
 */
public class RedundantMaxString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            Map<Character, Integer> map = new HashMap<>();

            for (char c : string.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            List<Map.Entry<Character, Integer>> list = new LinkedList<>();
            list.addAll(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    if (o1.getValue() - o2.getValue() == 0) {
                        return o1.getKey() - o2.getKey();
                    } else {
                        return o2.getValue() - o1.getValue();
                    }
                }
            });
            int time = list.get(0).getValue();
            char c = list.get(0).getKey();
            for (int i = 0; i < time; i++) {
                stringBuilder.append(c);
            }
            System.out.println(stringBuilder.toString());
        }
        scanner.close();
    }
}
