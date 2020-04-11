package niuke;

import java.util.*;

/**
 * 题目描述
 * 实现一个可存储若干个单词的字典。用户可以：
 * 1.在字典中加入单词
 * 2.查找指定单词在字典中的兄弟单词个数
 * 3.查找指定单词的指定序号的兄弟单词，指定序号指字典中兄弟单词按字典顺序排序后的序号
 * 4.清空字典中所有的单词
 *
 * 定义，格式说明
 * 1.单词：有小写英文字母组成，不含其它字符
 * 2.兄弟单词：
 * 给定一个单词X，如果通过任意交换单词中字母的位置得到不同的单词Y，那么定义Y是X的兄弟单词
 * 举例：bca是abc的兄弟单词，abc与abc是相同单词不是兄弟单词
 *
 * 题目规格
 * 1.0<=字典中所含单词个数<=1000
 * 2.1<=单词所含字母数<=50
 *
 * 输入描述:
 * 先输入字典中单词的个数，再输入n个单词作为字典单词。
 * 输入一个单词，查找其在字典中兄弟单词的个数
 * 再输入数字n
 *
 *
 * 输出描述:
 * 根据输入，输出查找到的兄弟单词的个数
 *
 * 输入例子:
 * 3
 * abc
 * bca
 * cab
 * abc
 * 1
 *
 * 输出例子:
 * 2
 * bca
 *
 * 做本题的时候几个坑需要注意：
 * 1.对于要查找的单词，可能包含若干个字符的重复，如aabbc，全排列后需要去重操作并且不能包含要查找的单词，选择TreeSet保存全部的兄弟单词集合
 * 2.对于字典中的所有单词，查找时候可能存在若干个相同的兄弟单词，都要按照字典序全部保存下来
 */
public class BrotherWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = scanner.next();
            }
            String keyWord = scanner.next();
            char[] keyWordArray = keyWord.toCharArray();
            Arrays.sort(keyWordArray);
            int index = scanner.nextInt();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (isBrotherWord(keyWord, strings[i], keyWordArray))
                    list.add(strings[i]);
            }
            Collections.sort(list);
            System.out.println(list.size());
            if (list.size() >= index) {
                System.out.println(list.get(index - 1));
            }
        }
        scanner.close();
    }

    private static boolean isBrotherWord(String key, String dictWord, char[] keyWordArray) {
        if (key.equals(dictWord) || key.length() != dictWord.length())
            return false;
        char[] dictWordArray = dictWord.toCharArray();
        Arrays.sort(dictWordArray);
        return Arrays.equals(keyWordArray, dictWordArray);
    }
}
