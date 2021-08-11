package airbnb;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 505. 迷宫 II
 * 由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 *
 * 给定球的起始位置，目的地和迷宫，找出让球停在目的地的最短距离。距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。如果球无法停在目的地，返回 -1。
 *
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 *
 *
 *
 * 示例 1:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
 *
 * 输出: 12
 *
 * 解析: 一条最短路径 : left -> down -> left -> down -> right -> down -> right。
 *              总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。
 *
 * 示例 2:
 *
 * 输入 1: 迷宫由以下二维数组表示
 *
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 *
 * 输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
 * 输入 3: 目的地坐标 (rowDest, colDest) = (3, 2)
 *
 * 输出: -1
 *
 * 解析: 没有能够使球停在目的地的路径。
 *
 *
 *
 * 注意:
 *
 * 迷宫中只有一个球和一个目的地。
 * 球和目的地都在空地上，且初始时它们不在同一位置。
 * 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
 * 迷宫至少包括2块空地，行数和列数均不超过100。
 */
@Component
public class TheMaze2 {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue< int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0],start[1],0});
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if(distance[s[0]][s[1]] < s[2])
                continue;
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
                }
            }
        }
    }
}
/**
 * 方法一：深度优先搜索
 * 我们可以用搜索树的形式来展开搜索空间。如下图所示，根节点代表起始位置，每个节点有 4 个孩子，表示 4 种不同的路线：左、右、上、下。经过某条路线到达一个新的节点，就表示在迷宫中选择某个方向滚动直到停止。
 *
 *
 *
 * 我们可以使用深度优先搜索对整颗搜索树进行遍历。我们从起始位置开始，每次选择一条路线进行搜索，并用计数器 count 记录当前的步数。为了防止重复搜索，我们需要使用一个二维数组 distance 记录从起始位置到 (i, j) 的最小步数 distance[i, j]，若在某一次搜索到位置 (i, j) 时，distance[i, j] 的值小于等于 count，那么我们可以进行回溯，不必继续搜索下去。
 *
 *
 * 1 / 31
 *
 * Java
 *
 * public class Solution {
 *     public int shortestDistance(int[][] maze, int[] start, int[] dest) {
 *         int[][] distance = new int[maze.length][maze[0].length];
 *         for (int[] row: distance)
 *             Arrays.fill(row, Integer.MAX_VALUE);
 *         distance[start[0]][start[1]] = 0;
 *         dfs(maze, start, distance);
 *         return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
 *     }
 *
 *     public void dfs(int[][] maze, int[] start, int[][] distance) {
 *         int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
 *         for (int[] dir: dirs) {
 *             int x = start[0] + dir[0];
 *             int y = start[1] + dir[1];
 *             int count = 0;
 *             while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
 *                 x += dir[0];
 *                 y += dir[1];
 *                 count++;
 *             }
 *             if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
 *                 distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
 *                 dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
 *             }
 *         }
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(MN\max(M,N))O(MNmax(M,N))，其中 MM 和 NN 是迷宫的高和宽。
 *
 * 空间复杂度：O(MN)O(MN)。
 *
 * 方法二：广度优先搜索
 * 我们同样可以使用广度优先搜索，实现细节与深度优先搜索类似。
 *
 * 注意在一般的广度优先搜索中，我们不会经过同一个节点超过一次，但在这道题目中，只要从起始位置到当前节点的步数 count 小于之前记录的最小步数 distance[i, j]，我们就会把 (i, j) 再次加入队列中。
 *
 * Java
 *
 * public class Solution {
 *     public int shortestDistance(int[][] maze, int[] start, int[] dest) {
 *         int[][] distance = new int[maze.length][maze[0].length];
 *         for (int[] row: distance)
 *             Arrays.fill(row, Integer.MAX_VALUE);
 *         distance[start[0]][start[1]] = 0;
 *          int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
 *         Queue < int[] > queue = new LinkedList < > ();
 *         queue.add(start);
 *         while (!queue.isEmpty()) {
 *             int[] s = queue.remove();
 *             for (int[] dir: dirs) {
 *                 int x = s[0] + dir[0];
 *                 int y = s[1] + dir[1];
 *                 int count = 0;
 *                 while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
 *                     x += dir[0];
 *                     y += dir[1];
 *                     count++;
 *                 }
 *                 if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
 *                     distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
 *                     queue.add(new int[] {x - dir[0], y - dir[1]});
 *                 }
 *             }
 *         }
 *         return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O(MN\max(M,N))O(MNmax(M,N))，其中 MM 和 NN 是迷宫的高和宽。
 *
 * 空间复杂度：O(MN)O(MN)。
 *
 * 方法三：Dijkstra 算法
 * 我们可以使用 Dijkstra 算法直接求出从起始位置到终点位置的最短路。这里不会详细介绍 Dijkstra 算法的实现，只会描述如何建立这道题对应的图。
 *
 * 对于迷宫中的任意一个空地 0，即为 x，它可以往四个方向滚动，假设它往上下左右分别可以滚动到位置 p, q, r, s，那么可以从 x 向 p, q, r, s 分别连一条权值为经过空地个数的边，注意这条边是单向边，因为从 x 可以滚动到位置 p 不代表从 p 一定可以滚动到位置 x。
 *
 * 在连完所有的边之后，我们以起始位置为源，使用 Dijkstra 算法计算出其到所有其它位置的最短路长度，也就得到了从起始位置到目的地最少经过的空地个数。
 *
 * Java
 *
 * public class Solution {
 *     public int shortestDistance(int[][] maze, int[] start, int[] dest) {
 *         int[][] distance = new int[maze.length][maze[0].length];
 *         boolean[][] visited = new boolean[maze.length][maze[0].length];
 *         for (int[] row: distance)
 *             Arrays.fill(row, Integer.MAX_VALUE);
 *         distance[start[0]][start[1]] = 0;
 *         dijkstra(maze, distance, visited);
 *         return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
 *     }
 *     public int[] minDistance(int[][] distance, boolean[][] visited) {
 *         int[] min={-1,-1};
 *         int min_val = Integer.MAX_VALUE;
 *         for (int i = 0; i < distance.length; i++) {
 *             for (int j = 0; j < distance[0].length; j++) {
 *                 if (!visited[i][j] && distance[i][j] < min_val) {
 *                     min = new int[] {i, j};
 *                     min_val = distance[i][j];
 *                 }
 *             }
 *         }
 *         return min;
 *     }
 *     public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
 *         int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
 *         while (true) {
 *             int[] s = minDistance(distance, visited);
 *             if (s[0] < 0)
 *                 break;
 *             visited[s[0]][s[1]] = true;
 *             for (int[] dir: dirs) {
 *                 int x = s[0] + dir[0];
 *                 int y = s[1] + dir[1];
 *                 int count = 0;
 *                 while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
 *                     x += dir[0];
 *                     y += dir[1];
 *                     count++;
 *                 }
 *                 if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
 *                     distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
 *                 }
 *             }
 *         }
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O\big(M^2N^2)O(M
 * 2
 *  N
 * 2
 *  )。
 *
 * 空间复杂度：O(MN)O(MN)。
 *
 * 方法四：Dijkstra 算法 + 堆优化
 * 我们可以使用堆（优先队列）优化 Dijkstra 算法，减少其时间复杂度。这是一种较为常用的优化方法，具体实现方式在这里也不赘述。
 *
 * Java
 *
 * public class Solution {
 *     public int shortestDistance(int[][] maze, int[] start, int[] dest) {
 *         int[][] distance = new int[maze.length][maze[0].length];
 *         for (int[] row: distance)
 *             Arrays.fill(row, Integer.MAX_VALUE);
 *         distance[start[0]][start[1]] = 0;
 *         dijkstra(maze, start, distance);
 *         return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
 *     }
 *
 *     public void dijkstra(int[][] maze, int[] start, int[][] distance) {
 *         int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
 *         PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
 *         queue.offer(new int[]{start[0],start[1],0});
 *         while (!queue.isEmpty()) {
 *             int[] s = queue.poll();
 *             if(distance[s[0]][s[1]] < s[2])
 *                 continue;
 *             for (int[] dir: dirs) {
 *                 int x = s[0] + dir[0];
 *                 int y = s[1] + dir[1];
 *                 int count = 0;
 *                 while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
 *                     x += dir[0];
 *                     y += dir[1];
 *                     count++;
 *                 }
 *                 if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
 *                     distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
 *                     queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
 *                 }
 *             }
 *         }
 *     }
 * }
 * 复杂度分析
 *
 * 时间复杂度：O\big(MN\log(MN)\big)O(MNlog(MN))。
 *
 * 空间复杂度：O(MN)O(MN)。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/the-maze-ii/solution/mi-gong-ii-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */