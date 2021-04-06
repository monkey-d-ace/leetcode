package airbnb;

import java.util.*;

/**
 * 火星词典
 *
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 *
 * 假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个 不为空的 单词列表。因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。
 *
 * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 输出: "wertf"
 * 示例 2：
 *
 * 输入:
 * [
 *   "z",
 *   "x"
 * ]
 * 输出: "zx"
 * 示例 3：
 *
 * 输入:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 * 输出: "" 
 * 解释: 此顺序是非法的，因此返回 ""。
 *  
 *
 * 提示：
 *
 * 你可以默认输入的全部都是小写字母
 * 若给定的顺序是不合法的，则返回空字符串即可
 * 若存在多种可能的合法字母顺序，请返回其中任意一种顺序即可
 *
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"abc", "ab"};
        System.out.println(new AlienDictionary().alienOrder(words));

        words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(new AlienDictionary().alienOrder(words));
    }

    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.max(words[i].length(), words[i + 1].length());

            for (int j = 0; j < len; j++) {
                if (j >= words[i].length()) {
                    break;
                }
                if (j >= words[i + 1].length()) {
                    return "";
                }
                if (words[i].charAt(j) == words[i + 1].charAt(j)) {
                    continue;
                }
                Set<Character> set = graph.computeIfAbsent(words[i].charAt(j), key -> new HashSet<>());
                set.add(words[i + 1].charAt(j));
                break;
            }
        }

        LinkedList<Character> queue = new LinkedList<>();
        int[] inDegree = new int[26];

        Arrays.fill(inDegree, -1);
        int numChar = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree[c - 'a'] = 0;
            }
        }

        for (char key : graph.keySet()) {
            for (char val : graph.get(key)) {
                inDegree[val - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                queue.add((char)(i + 'a'));
            }

            if (inDegree[i] != -1) {
                numChar++;
            }
        }

        while (!queue.isEmpty()) {
            Character firstChar = queue.poll();
            stringBuilder.append(firstChar);
            if (graph.containsKey(firstChar)) {
                for (char val : graph.get(firstChar)) {
                    inDegree[val - 'a']--;
                    if (inDegree[val - 'a'] == 0) {
                        queue.add(val);
                    }
                }
            }
        }
        if (numChar != stringBuilder.length())
            return "";
        return stringBuilder.toString();
    }
}
