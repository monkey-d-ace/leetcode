package airbnb;

import java.util.HashSet;
import java.util.Set;

/**
 * Given lots of rectangles, output how many of them intersect.
 */
public class NumberOfIntersectedRectangles {
    public static void main(String[] args) {
        NumberOfIntersectedRectangles numberOfIntersectedRectangles = new NumberOfIntersectedRectangles();
        int[][][] rectangles = {
                {{-3, -2}, {2, 1}},
                {{10, 8}, {15, 10}},
                {{1, 0}, {7, 4}},
                {{12, 9}, {16, 12}},
                {{-2, -1}, {5, 3}}
        };

        System.out.println(numberOfIntersectedRectangles.countIntersection(rectangles));
    }
    private boolean intersect(int[][] r1, int[][] r2) {
        return r1[0][0] < r2[0][0] && r1[0][1] < r2[0][1] && r2[0][0] < r1[1][0] && r2[0][1] < r1[1][1] ||
                r1[0][0] < r2[1][0] && r1[0][1] < r2[1][1] && r2[1][0] < r1[1][0] && r2[1][1] < r1[1][1];
    }

    private int find(int val, int[] parents) {
        while (parents[val] != val) {
            val = parents[val];
        }
        return val;
    }

    public int countIntersection(int[][][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return 0;
        int n = rectangles.length;
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersect(rectangles[i], rectangles[j])) {
                    int root1 = find(i, parents);
                    int root2 = find(j, parents);

                    if (root1 != root2)
                        parents[root1] = root2;
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i, parents));
        }
        return set.size();
    }
}
