package airbnb;

import java.util.*;

/**
 * Given a flight itinerary consisting of starting city, destination city, and ticket price (2d list) - find the optimal price flight path to get from start to destination. (A variation of Dynamic Programming Shortest Path)
 */
public class MinimumCostWithAtMostKStops {
    public static void main(String[] args) {
        MinimumCostWithAtMostKStops minimumCostWithAtMostKStops = new MinimumCostWithAtMostKStops();
        List<String> lines = new ArrayList<>();
        lines.add("A->B,100");
        lines.add("A->C,400");
        lines.add("B->C,100");
        lines.add("C->D,100");
        lines.add("C->A,10");
        System.out.println(minimumCostWithAtMostKStops.minCost(lines, "A", "D", 0));
        System.out.println(minimumCostWithAtMostKStops.minCost(lines, "A", "D", 1));
        System.out.println(minimumCostWithAtMostKStops.minCost(lines, "A", "D", 2));
    }
    class Flight {
        String name;
        int minCost;
        Map<String, Integer> nextNodes;

        Flight(String name) {
            this.name = name;
            this.minCost = Integer.MAX_VALUE;
            this.nextNodes = new HashMap<>();
        }
    }
    public int minCost(List<String> lines, String source, String target, int k) {
        if (lines.size() == 0 || k < 0)
            return 0;
        Map<String, Flight> nodes = new HashMap<>();
        for (String line : lines) {
            String[] s = line.trim().split(",");
            String[] t = s[0].trim().split("->");
            String from = t[0];
            String to = t[1];
            int cost = Integer.valueOf(s[1].trim());
            if (!nodes.containsKey(from)) nodes.put(from, new Flight(from));
            if (!nodes.containsKey(to)) nodes.put(to, new Flight(to));
            nodes.get(from).nextNodes.put(to, cost);
        }

        boolean find = false;
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> cost = new LinkedList<>();
        queue.offer(source);
        cost.offer(0);
        int stops = -1;
        while (!queue.isEmpty()) {
            stops++;
            if (stops > k + 1)
                break;
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                Flight curr = nodes.get(queue.poll());
                int currCost = cost.poll();
                curr.minCost = Math.min(curr.minCost, currCost);

                if (curr.name.equals(target)) {
                    find = true;
                    continue;
                }

                for (String next : curr.nextNodes.keySet()) {
                    int nextCost = currCost + curr.nextNodes.get(next);
                    if (nextCost < nodes.get(next).minCost && (stops < k || stops == k && next.equals(target))) {
                        queue.offer(next);
                        cost.offer(nextCost);
                    }
                }
            }
        }
        return find ? nodes.get(target).minCost : -1;
    }
}
