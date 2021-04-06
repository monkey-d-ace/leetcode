package airbnb;

import java.util.*;

/**
 * There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard. Define the cost between wizards and wizard as square of different of i and j. To find the min cost between 0 and 9.
 *
 * For example:
 *
 * wizard[0] list: 1, 4, 5 
 *
 * wizard[4] list: 9
 *
 *  wizard 0 to 9 min distance is (0-4)^2+(4-9)^2=41 (wizard[0]->wizard[4]->wizard[9])
 */
public class Wizards {
    public static void main(String[] args) {
        Wizards wizards = new Wizards();
        int[][] ids = {{1, 5, 9}, {2, 3, 9}, {4}, {}, {}, {9}, {}, {}, {}, {}};
        List<List<Integer>> wizardList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            List<Integer> wizard = new ArrayList<>();
            for (int j = 0; j < ids[i].length; j++) {
                wizard.add(ids[i][j]);
            }
            wizardList.add(wizard);
        }

        List<Integer> res = wizards.getShortestPath(wizardList, 0, 9);
        System.out.println(res);
        System.out.println(res.get(0));
        System.out.println(res.get(1));
        System.out.println(res.get(2));
    }
    class Wizard implements Comparable<Wizard> {
        int id;
        int dist;
        Wizard(int id) {
            this.id = id;
            this.dist = Integer.MAX_VALUE;
        }
        @Override
        public int compareTo(Wizard o) {
            return this.dist - o.dist;
        }
    }

    public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
        int n = wizards.size();
        int[] parents = new int[wizards.size()];
        Map<Integer, Wizard> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            map.put(i, new Wizard(i));
        }

        map.get(source).dist = 0;
        Queue<Wizard> queue = new LinkedList<>();
        queue.offer(map.get(source));
        while (!queue.isEmpty()) {
            Wizard curr = queue.poll();
            List<Integer> neighbors = wizards.get(curr.id);
            for (int neighbor : neighbors) {
                Wizard next = map.get(neighbor);
                int weight = (int)Math.pow(next.id - curr.id, 2);
                if (curr.dist + weight < next.dist) {
                    parents[next.id] = curr.id;
                    next.dist = curr.dist + weight;
                }
                queue.offer(next);
            }
        }
        List<Integer> res = new ArrayList<>();
        int t = target;
        while (t != source) {
            res.add(t);
            t = parents[t];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }
}
