package airbnb;

import java.util.*;

/**
 * I give you a box
 *
 * A box can either be locked or unlocked
 * Inside the box you will find: # candy, # inner boxes, # keys
 * Keys correspond to specific boxes
 * What is maximum number of candy you can retrieve?
 *
 * EXAMPLE
 *
 * I gave you box A
 *
 * A = { unlocked, 7 candy, boxes=[B, C], keys=[] }
 *
 * B = { locked, 5 candy, boxes=[D], keys=[] }
 *
 * C = { unlocked, 4 candy, boxes=[], keys=[B] }
 *
 * D = { locked, 100 candy, boxes=[], keys=[] }
 *
 * I give you box A, what is the maximum # of candy you can retrieve? 16
 *
 * HINT 1: Think, what are the conditions in which a box is "ready" to be opened?
 *
 * If you discover a new box and it is already unlocked
 * I have previously found key X, and now I find locked box X
 * I find key Y, and have already seen locked box Y
 */
public class BoxCandies {
    static class Box {
        String id;
        int candy;
        List<Box> innerBoxes;
        List<String> keys;
        boolean locked;

        public Box(String id, int candy, boolean locked) {
            this.id = id;
            this.candy = candy;
            innerBoxes = new ArrayList<>();
            keys = new ArrayList<>();
            this.locked = locked;
        }
    }
    public static void main(String[] args) {
        Box dBox = new Box("D", 100, true);
        Box bBox = new Box("B", 5, true);
        bBox.innerBoxes.add(dBox);
        Box cBox = new Box("C", 4, false);
        cBox.keys.add("B");
        Box aBox = new Box("A", 7, false);
        aBox.innerBoxes.add(bBox);
        aBox.innerBoxes.add(cBox);
        System.out.println(new BoxCandies().getMaxValue(aBox));
    }
    public int bfs(Box abox, Set<String> keys) {
        int res = 0;
        Set<Box> cache = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Queue<Box> q = new LinkedList<>();
        q.offer(abox);
        while (!q.isEmpty()) {
            Box cur = q.poll();
            if (cur.locked && !keys.contains(cur.id)) {
                cache.add(cur);
            } else {
                if (visited.contains(cur.id))
                    continue;
                visited.add(cur.id);
                res += cur.candy;
                keys.addAll(cur.keys);
                for (Box b : cache) {
                    if (keys.contains(b.id))
                        q.offer(b);
                }
                for (Box nei : cur.innerBoxes)
                    q.offer(nei);
            }
        }
        return res;
    }

    public int getMaxValue(Box aBox) {
        Set<String> keys = new HashSet<>();
        return bfs(aBox, keys);
    }


}
