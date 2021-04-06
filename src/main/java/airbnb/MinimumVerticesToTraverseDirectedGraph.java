package airbnb;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Given a directed grapjh, represented in a two dimension array, output a list of points that can be used to travese every points with the least number of visited vertices.
 */
public class MinimumVerticesToTraverseDirectedGraph {
    public static void main(String[] args) {
        MinimumVerticesToTraverseDirectedGraph min = new MinimumVerticesToTraverseDirectedGraph();
        int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        List<Integer> res = min.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());

        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
        res = min.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());
        edges = new int[][]{{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
        res = min.getMin(edges, 10);
        System.out.println(res);
        System.out.println(res.size());
    }
    private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start, Set<Integer> visited, Set<Integer> currVisited) {
        currVisited.add(cur);
        visited.add(cur);
        for (int next : nodes.get(cur)) {
            if (res.contains(next) && next != start) {
                res.remove(next);
            }

            if (!currVisited.contains(next)) {
                search(res, nodes, next, start, visited, currVisited);
            }
        }
    }

    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res.add(i);
                visited.add(i);
                search(res, nodes, i, i, visited, new HashSet<>());
            }
        }
        return new ArrayList<>(res);
    }
}
