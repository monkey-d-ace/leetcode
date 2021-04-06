package airbnb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given: An array of strings where L indicates land and W indicates water, and a coordinate marking a starting point in the middle of the ocean.
 *
 * Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
 *
 * An ocean coordinate is defined to be the initial coordinate if a W, and
 * any coordinate directly adjacent to any other ocean coordinate.
 * void findOcean(String[] map, int row, int column);
 * String[] map = new String[]{
 *  "WWWLLLW",
 *  "WWLLLWW",
 *  "WLLLLWW"
 * };
 * printMap(map);
 * STDOUT:
 * WWWLLLW
 * WWLLLWW
 * WLLLLWW
 * findOcean(map, 0, 1);
 * printMap(map);
 * STDOUT:
 * OOOLLLW
 * OOLLLWW
 * OLLLLWW
 */
public class FindingOcean {
    public static void main(String[] args) {
        FindingOcean ocean = new FindingOcean();
        List<String> testData = new ArrayList<String>() {{
            add("WWWLLLW");
            add("WWLLLWW");
            add("WLLLLWW");
        }};
        char[][] map = new char[testData.size()][testData.get(0).length()];
        for (int i = 0; i < testData.size(); i++)
            for (int j = 0; j < testData.get(i).length(); j++)
                map[i][j] = testData.get(i).charAt(j);

        ocean.floodFill(map, 0, 0, 'W', 'O');
        System.out.println(map[0][0]);

        testData = new ArrayList<String>() {{
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLWLLLLL");
            add("LLWWLLLLLLLLLLLLLLLL");
            add("LLWWLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLWWLLLLLLLLLLL");
            add("LLLLLLLLWWLLLLLLLLLL");
            add("LLLLLLLLLWWWLLLLLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLWWLLLLWWWWWWLLLL");
            add("LLLLWWWLLLWWWWWWWWWW");
            add("LLLLLWWWWWWWWWWWLLLL");
            add("LLLLLLLLLLLLLLWWWWLL");
            add("LLLLLLLLLLLLLLWLLLLL");
            add("LLLLWLLLLLLLLLLLLWLL");
            add("LLLLLLLLLLLLLLLLLLWL");
        }};
        map = new char[testData.size()][testData.get(0).length()];
        for (int i = 0; i < testData.size(); i++)
            for (int j = 0; j < testData.get(i).length(); j++)
                map[i][j] = testData.get(i).charAt(j);
        ocean.floodFill(map, 9, 12, 'W', 'O');
        System.out.println(map[9][11]);
    }
    public void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
        if (board[i][j] != oldColor || board[i][j] == newColor) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * board[0].length + 1);
        board[i][j] = newColor;

        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int m = pos / board[0].length;
            int n = pos % board[0].length;

            if (m + 1 < board.length && board[m + 1][n] == oldColor) {
                queue.offer((m + 1) * board[0].length + n);
                board[m + 1][n] = newColor;
            }

            if (m - 1 >= 0 && board[m - 1][n] == oldColor) {
                queue.offer((m - 1) * board[0].length + n);
                board[m - 1][n] = newColor;
            }

            if (n + 1 < board[0].length && board[m][n + 1] == oldColor) {
                queue.offer(m * board[0].length + n + 1);
                board[m][n + 1] = newColor;
            }

            if (n - 1 >= 0 && board[m][n - 1] == oldColor) {
                queue.offer(m * board[0].length + n - 1);
                board[m][n - 1] = newColor;
            }
        }
    }
}
