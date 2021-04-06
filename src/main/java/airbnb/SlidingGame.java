package airbnb;

import java.util.*;

/**
 * You're given a 3x3 board of a tile puzzle, with 8 tiles numbered 1 to 8, and an empty spot. You can move any tile adjacent to the empty spot, to the empty spot, creating an empty spot where the tile originally was. The goal is to find a series of moves that will solve the board, i.e. get [ [1, 2, 3], [4, 5, 6], [7, 8, - ]…
 */
public class SlidingGame {
    private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int[][] matrix;
    private int m;
    private int n;
    private int originX;
    private int originY;
    private String recovered;

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 1, 4},
                {6, 2, 0},
                {7, 5, 8}
        };
        SlidingGame game = new SlidingGame(matrix);
        System.out.println(game.canSolve());
        List<String> res = game.getSolution();
        System.out.println(res);
    }
    public SlidingGame(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        int[][] recoveredMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    this.originX = i;
                    this.originY = j;
                }

                recoveredMatrix[i][j] = i * n + j;
            }
        }
        this.recovered = getMatrixString(recoveredMatrix);
    }

    public boolean canSolve() {
        Queue<int[]> items = new LinkedList<>();
        Queue<String> matrix = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        String stringMatrix = getMatrixString(this.matrix.clone());
        items.offer(new int[]{originX, originY});
        matrix.offer(stringMatrix);
        visited.add(stringMatrix);

        while (!items.isEmpty()) {
            int size = items.size();
            for (int i = 0; i < size; i++) {
                int[] curElement = items.poll();
                String curMatrixString = matrix.poll();
                int x = curElement[0];
                int y = curElement[1];

                if (curMatrixString.equals(recovered)) {
                    return true;
                }

                for (int k = 0; k < dirs.length; k++) {
                    int xx = x + dirs[k][0];
                    int yy = y + dirs[k][1];

                    if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                        continue;
                    }

                    int[][] newMatrix = recoverMatrixString(curMatrixString);
                    int temp = newMatrix[x][y];
                    newMatrix[x][y] = newMatrix[xx][yy];
                    newMatrix[xx][yy] = temp;
                    String newMatrixString = getMatrixString(newMatrix);
                    if (visited.contains(newMatrixString)) {
                        continue;
                    }

                    items.offer(new int[]{xx, yy});
                    matrix.offer(newMatrixString);
                    visited.add(newMatrixString);
                }
            }
        }

        return false;
    }

    public List<String> getSolution() {
        String[] pathWord = {"Down", "Right", "Up", "Left"};

        Queue<int[]> items = new LinkedList<>();
        Queue<String> matrix = new LinkedList<>();
        Queue<List<String>> path = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        String stringMatrix = getMatrixString(this.matrix.clone());
        items.offer(new int[]{originX, originY});
        matrix.offer(stringMatrix);
        path.offer(new ArrayList<>());
        visited.add(stringMatrix);

        while (!items.isEmpty()) {
            int size = items.size();
            for (int i = 0; i < size; i++) {
                int[] curElement = items.poll();
                String curMatrixString = matrix.poll();
                List<String> curPath = path.poll();
                int x = curElement[0];
                int y = curElement[1];

                if (curMatrixString.equals(recovered)) {
                    return curPath;
                }

                for (int k = 0; k < dirs.length; k++) {
                    int xx = x + dirs[k][0];
                    int yy = y + dirs[k][1];

                    if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                        continue;
                    }

                    int[][] newMatrix = recoverMatrixString(curMatrixString);
                    int temp = newMatrix[x][y];
                    newMatrix[x][y] = newMatrix[xx][yy];
                    newMatrix[xx][yy] = temp;
                    String newMatrixString = getMatrixString(newMatrix);
                    if (visited.contains(newMatrixString)) {
                        continue;
                    }

                    List<String> newPath = new ArrayList<>(curPath);
                    newPath.add(pathWord[k]);

                    items.offer(new int[]{xx, yy});
                    matrix.offer(newMatrixString);
                    path.offer(newPath);
                    visited.add(newMatrixString);
                }
            }
        }

        return new ArrayList<>();
    }

    private String getMatrixString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j]).append(",");
            }
        }
        return sb.toString();
    }

    private int[][] recoverMatrixString(String str) {
        String[] parts = str.split(",");
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = Integer.parseInt(parts[i * n + j]);
            }
        }
        return res;
    }
}
