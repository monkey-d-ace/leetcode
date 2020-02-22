package niuke;

import java.util.*;

/**
 * 合并表记录
 * 题目描述
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 *
 * 输入描述:
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 *
 * 输出描述:
 * 输出合并后的键值对（多行）
 *
 * 示例1
 * 输入
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出
 * 0 3
 * 1 2
 * 3 4
 */
public class MergeRecord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            for (int i = 0; i < num; i++) {
                int k = scanner.nextInt();
                int v = scanner.nextInt();
                if (!map.containsKey(k)) {
                    map.put(k, v);
                } else {
                    map.put(k, map.get(k) + v);
                }
            }
            Set<Map.Entry<Integer, Integer>> set = map.entrySet();

            for (Map.Entry<Integer, Integer> entry : set) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        scanner.close();
    }
}
