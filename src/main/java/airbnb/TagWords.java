package airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assume that Airbnb decided to enable search on user reviews. In order to make the search better you were requested to tag specific words in the review .
 *
 * Search team has given you a bunch of key value pairs where the keys denote words which may or may not present in a review and the value corresponds to the search metadata. Your task is to prepend the metadata tag and highlight the specific review word in brackets. You should make sure the original case is maintained in the result string.
 *
 * Test case #1 [ no confliction ]
 *
 * Input:
 *
 * Review:
 * "I visited San Francisco for work and stayed at Airbnb.
 * I really loved the city and the home where I stayed."
 *
 * Tags: {
 * "airbnb": "business",
 * "san francisco": "city",
 * }
 *
 * Output:
 * "I visited [city]{San Francisco} for work and stayed at [business]{Airbnb}.
 * I really loved the city and the home where I stayed."
 *
 * Test case #2 [ with confliction ]
 *
 * Input
 *
 * Review:
 * "I travelled to San Francisco for work and stayed at Airbnb.
 * I really loved the city and the home where I stayed.
 * I stayed with San and Francisco.
 * They both were really good and san's hospitality was outstanding."
 *
 * Tags: {
 * "san": "person",
 * "francisco": "person",
 * "san francisco": "city",
 * "Airbnb": "business",
 * "city": "location",
 * }
 *
 * Output:
 * "I travelled to [city]{San Francisco} for work and stayed at [business]{Airbnb}.
 * I really loved the [location]{city} and the home where I stayed.
 * I stayed with [person]{San} and [person]{Francisco}.
 * They both were really good and [person]{san}'s hospitality was outstanding."
 */
public class TagWords {
    public static void main(String[] args) {
        TagWords tagWords = new TagWords();
        String s = "I visited San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed.";
        String[][] tags = {{"airbnb", "business"}, {"san francisco", "city"}};
        System.out.println(tagWords.prepareTags(s, tags));

        String s1 = "I travelled to San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed.I stayed with San and Francisco. They both were really good and san's hospitality was outstanding.";
        String[][] tags1 = {{"san", "person"},
                {"francisco", "person"},
                {"san francisco", "city"},
                {"Airbnb", "business"},
                {"city", "location"}};
        System.out.println(tagWords.prepareTags(s1, tags1));
    }

    public String prepareTags(String str, String[][] tags) {
        Map<String, Integer> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        for (String[] tag : tags) {
            if (!map.containsKey(tag[1])) {
                map.put(tag[1], names.size());
                names.add(tag[1]);
            }
        }
        Trie root = new Trie();
        for (String[] tag : tags) {
            prepareTrie(root, tag[0].toLowerCase(), map.get(tag[1]));
        }

        StringBuilder sb = new StringBuilder();
        int idx = -1;
        int next = -1;
        for (int i = 0; i < str.length(); ) {
            int j = i;
            Trie node = root;
            while (j < str.length() && node.c != null && node.c[Character.toLowerCase(str.charAt(j))] != null) {
                node = node.c[Character.toLowerCase(str.charAt(j++))];
                if (node.idx != null) {
                    idx = node.idx;
                    next = j;
                }
            }
            if (idx != -1) {
                sb.append("[" + names.get(idx) + "]{" + str.substring(i, next) + "}");
                idx = -1;
                i = next;
            } else {
                sb.append(str.charAt(i++));
            }
        }
        return sb.toString();
    }

    public void prepareTrie(Trie root, String tag, int index) {
        Trie node = root;
        for (char ch : tag.toCharArray()) {
            if (node.c == null) {
                node.c = new Trie[256];
            }
            if (node.c[ch] == null) {
                node.c[ch] = new Trie();
            }
            node = node.c[ch];
        }
        node.idx = index;
    }

    class Trie {
        Trie[] c;
        Integer idx;
    }
}
