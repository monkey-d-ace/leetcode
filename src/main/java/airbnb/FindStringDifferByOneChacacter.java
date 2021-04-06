package airbnb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of n strings. Each string has length k. Return true if there're 2 strings that only differ by 1 character, otherwise false. You can assume that all strings contain only lowercase English letters [a-z].
 *
 * Example 1:
 *
 * Input: ["abc", "xyz", "abd"]
 * Output: true
 * Explanation: "abc" and "abd" only differ by 1 character.
 * Example 2:
 *
 * Input: ["abc", "def", "xyz"]
 * Output: false
 * Example 3:
 *
 * Input: ["abcd", "bbbb", "abxd", "cccc"]
 * Output: true
 */
public class FindStringDifferByOneChacacter {
    public static void main(String[] args) {
        String[] strs = new String[]{"abc", "xyz", "abd"};
        System.out.println(new FindStringDifferByOneChacacter().existsOneDiff(strs));
        strs = new String[]{"abc", "def", "xyz"};
        System.out.println(new FindStringDifferByOneChacacter().existsOneDiff(strs));
        strs = new String[] {"abcd", "bbbb", "abxd", "cccc"};
        System.out.println(new FindStringDifferByOneChacacter().existsOneDiff(strs));
    }

    public boolean existsOneDiff(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            word = new String(letters);
            for (int i = 0; i < letters.length; i++) {
                String str = word.substring(0, i) + word.substring(i + 1);
                if (wordSet.contains(str))
                    return true;
                wordSet.add(str);
            }
        }
        return false;
    }
}
