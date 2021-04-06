package airbnb;

/**
 * Given (x, y, iter), in which (x, y) is position at x-axis and y-axis, and iter is how many iterations will be. Output is in iteration iter, how many steps are required to move from (0, 0) to (x, y) in iteration iter.
 */
public class HilbertCurve {
    public static void main(String[] args) {
        HilbertCurve curve = new HilbertCurve();
        System.out.println(curve.hilbertCurve(1, 1, 2));
        System.out.println(curve.hilbertCurve(0, 1, 1));
        System.out.println(curve.hilbertCurve(2, 2, 2));
    }
    public int hilbertCurve(int x, int y, int iter) {
        if (iter == 0) return 1;
        int len = 1 << (iter - 1);
        int num = 1 << (2 * (iter - 1));

        if (x >= len && y >= len) {
            // 3 Shape is identical with previous iteration
            return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
        } else if (x < len && y >= len) {
            // 2 Shape is identical with previous iteration
            return num + hilbertCurve(x, y - len, iter - 1);
        } else if (x < len && y < len) {
            // 1 Clock-wise rotate 90
            return hilbertCurve(y, x, iter - 1);
        } else {
            // 4 Anti-Clockwise rotate 90
            return 3 * num + hilbertCurve(len - y - 1, 2 * len - x - 1, iter - 1);
        }
    }
}
