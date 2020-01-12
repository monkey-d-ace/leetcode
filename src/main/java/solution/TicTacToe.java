package solution;

/**
 * 判定井字棋胜负
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 *
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 *
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 *       1. 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 *
 *       2. 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 *
 *       3. 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 *
 * 示例:
 *
 * 给定棋盘边长 n = 3, 玩家 1 的棋子符号是 "X"，玩家 2 的棋子符号是 "O"。
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> 函数返回 0 (此时，暂时没有玩家赢得这场对决)
 * |X| | |
 * | | | |    // 玩家 1 在 (0, 0) 落子。
 * | | | |
 *
 * toe.move(0, 2, 2); -> 函数返回 0 (暂时没有玩家赢得本场比赛)
 * |X| |O|
 * | | | |    // 玩家 2 在 (0, 2) 落子。
 * | | | |
 *
 * toe.move(2, 2, 1); -> 函数返回 0 (暂时没有玩家赢得比赛)
 * |X| |O|
 * | | | |    // 玩家 1 在 (2, 2) 落子。
 * | | |X|
 *
 * toe.move(1, 1, 2); -> 函数返回 0 (暂没有玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 2 在 (1, 1) 落子。
 * | | |X|
 *
 * toe.move(2, 0, 1); -> 函数返回 0 (暂无玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 1 在 (2, 0) 落子。
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> 函数返回 0 (没有玩家赢得比赛)
 * |X| |O|
 * |O|O| |    // 玩家 2 在 (1, 0) 落子.
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> 函数返回 1 (此时，玩家 1 赢得了该场比赛)
 * |X| |O|
 * |O|O| |    // 玩家 1 在 (2, 1) 落子。
 * |X|X|X|
 *  
 *
 * 进阶:
 * 您有没有可能将每一步的 move() 操作优化到比 O(n2) 更快吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-tic-tac-toe
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int anti_diagonal;
    private int size;

    public TicTacToe(int n) {
        this.size = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.diagonal = 0;
        this.anti_diagonal = 0;
    }

    public int move(int row, int col, int player) {
        int addMove = player == 1 ? 1 : -1;
        rows[row] += addMove;
        cols[col] += addMove;
        if (row == col)
            diagonal += addMove;
        if (row + col == size - 1)
            anti_diagonal += addMove;
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size || Math.abs(anti_diagonal) == size)
            return player;
        return 0;
    }
}