package airbnb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of string array,
 * In each string array, the first element consists of the rest elements.
 * e.g.
 * [Earth, South America, North America, Asia, Pacific]
 * [Asia, China, Korea, Japan]
 * [North America, USA, Canada]
 * [South America, Brazil, Columbia]
 * [Africa, Algeria, Lybia]
 * [China, Beijing, Shanhai]
 * [Japan, Tokyo, Kyoto]
 * [Korea, Seoul]
 *
 * Given two valid elements, find its least common ancestor (LCA)
 * e.g.
 * input
 * [Tokyo, Kyoto]
 * output
 * Japan
 *
 * input
 * [Beijing, Japan]
 * output
 * Asia
 *
 * input
 * [Seoul, Africa]
 * output
 * Earth
 */
public class LowestCommonAncestorOfTwoRegions {
    public static void main(String[] args) {
        String[][] regions = {
                {"Earth", "South America", "North America", "Asia", "Africa", "Pacific"},
                {"Asia", "China", "Korea", "Japan", "India"},
                {"North America", "USA", "Canada"},
                {"South America", "Brazil", "Columbia"},
                {"Africa", "Algeria", "Libya"},
                {"China", "Beijing", "Shanghai"},
                {"Japan", "Tokyo", "Kyoto"},
                {"India", "Hyderabad", "Chennai"},
                {"Korea", "Seoul"},
                {"USA", "CA", "WY", "NY"}

        };
        System.out.println(new LowestCommonAncestorOfTwoRegions(regions).getLowestCommonAncestorOfTwoRegions("Asia", "North America"));
        System.out.println(new LowestCommonAncestorOfTwoRegions(regions).getLowestCommonAncestorOfTwoRegions("Beijing", "Japan"));
        System.out.println(new LowestCommonAncestorOfTwoRegions(regions).getLowestCommonAncestorOfTwoRegions("CA", "USA"));
        System.out.println(new LowestCommonAncestorOfTwoRegions(regions).getLowestCommonAncestorOfTwoRegions("USA", "USA"));

    }
    Map<String, String> parents;
    public LowestCommonAncestorOfTwoRegions(String[][] regions) {
        parents = new HashMap<>();
        for (int i = 0; i < regions.length; i++) {
            String region = regions[i][0];
            for (int j = 1; j < regions[i].length; j++) {
                parents.put(regions[i][j], region);
            }
        }
    }

    public String getLowestCommonAncestorOfTwoRegions(String region1, String region2) {
        Set<String> set = new HashSet<>();
        if (region1.equals(parents.get(region2)))
            return region1;
        else if (region2.equals(parents.get(region1)))
            return region2;
        while (parents.containsKey(region1) || parents.containsKey(region2)) {
            if (parents.containsKey(region1)) {
                region1 = parents.get(region1);
            }
            if (parents.containsKey(region2)) {
                region2 = parents.get(region2);
            }
            if (set.contains(region1) || region1.equals(region2))
                return region1;
            if (set.contains(region2))
                return region2;
            set.add(region1);
            set.add(region2);
        }
        return "NA";
    }
}
