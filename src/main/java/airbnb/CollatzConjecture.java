package airbnb;

/**
 * If a number is odd, the next transform is 3*n+1
 * If a number is even, the next transform is n/2
 * The number is finally transformed into 1.
 * The step is how many transforms needed for a number turned into 1.
 * Given an integer n, output the max steps of transform number in [1, n] into 1.
 */
public class CollatzConjecture {
    public static void main(String[] args) {
        int num = 6;
        System.out.println(new CollatzConjecture().findLongestSteps(num));
    }

    public int findLongestSteps(int num) {
        if (num < 1) return 0;
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int t = findSteps(i);
            res = Math.max(res, t);
        }
        return res;
    }

    private int findSteps(int num) {
        if (num <= 1) return 1;
        if (num % 2 == 0) return 1 + findSteps(num / 2);
        return 1 + findSteps(3 * num + 1);
    }
}
