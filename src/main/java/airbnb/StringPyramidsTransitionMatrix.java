package airbnb;

import java.util.*;

/**
 * Given a list of leaf nodes in a pyramid, and a map which indicates what's the possible parent node given a left and right node. Return true if the one of leaf node could turn into the root node, Otherwise, return false. For example:
 *
 *      root
 *      / \
 *     X   X
 *    /\  /\
 *    X  X  X
 *   / \/ \/ \
 *  A   B  C  D
 * Map:
 *
 *         left: A |  B   |   C | D
 * right---------------------------------
 * A             B |A or C|   D | A
 * B             D |B or C|   A |
 * C                              B
 * D
 * Note:1. If left child is B, right child is A, the parent node could be B or C
 *
 * Given leaf input of "ABCD", output true.
 */
public class StringPyramidsTransitionMatrix {
    public static void main(String[] args) {
        String[] lines = {
                "A,A,AC", "A,B,CD", "A,C,D", "A,D,B",
                "B,A,B", "B,B,C", "B,C,A", "B,D,CD",
                "C,A,A", "C,B,C", "C,C,D", "C,D,B",
                "D,A,BC", "D,B,D", "D,C,A", "D,D,C"
        };
        StringPyramidsTransitionMatrix stringPyramidsTransitionMatrix = new StringPyramidsTransitionMatrix(lines);
        System.out.println(stringPyramidsTransitionMatrix.check("ABCD"));
        System.out.println(stringPyramidsTransitionMatrix.check("AACC"));
        System.out.println(stringPyramidsTransitionMatrix.check("AAAA"));
        System.out.println(stringPyramidsTransitionMatrix.check("CCCC"));
        System.out.println(stringPyramidsTransitionMatrix.check("DDDD"));
    }
    Map<String, Set<Character>> map;
    private Map<String, Boolean> cache;
    final String SEP = "###";
    public StringPyramidsTransitionMatrix(String[] line) {
        cache = new HashMap<>();
        map = new HashMap<>();
        for (String s : line) {
            String[] splitted = s.split(",");
            String key = splitted[0] + SEP + splitted[1];
            Set<Character> set = new HashSet<>();
            for (char c : splitted[2].toCharArray()) {
                set.add(c);
            }
            map.put(key, set);
        }
    }
    private void getNextLevel(List<String> res, String curr, int start, StringBuilder stringBuilder) {
        if (start == curr.length() - 1) {
            res.add(new String(stringBuilder));
            return;
        }

        for (int i = start; i < curr.length() - 1; i++) {
            String key = curr.charAt(i) + SEP + curr.charAt(i + 1);
            for (char c : map.get(key)) {
                stringBuilder.append(c);
                getNextLevel(res, curr, start + 1, stringBuilder);
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
        }
    }

    private boolean search(String input, String current) {
        if (cache.containsKey(input)) return cache.get(input);
        if (current.length() == 1) {
            cache.put(current, input.contains(current));
            return cache.get(current);
        }

        List<String> cands = new ArrayList<>();
        getNextLevel(cands, current, 0, new StringBuilder());
        for (String cand : cands) {
            // System.out.println(cand);
            if (cache.containsKey(cand)) return cache.get(cand);
            boolean res = search(input, cand);
            if (res) {
                cache.put(cand, true);
                return true;
            }
        }

        return false;
    }

    public boolean check(String input) {
        cache.clear();
        return search(input, input);
    }
}
