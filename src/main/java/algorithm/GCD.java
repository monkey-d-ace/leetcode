package algorithm;

public class GCD {
    public static void main(String[] args) {
        System.out.println(gcd(9, 6));
    }

    private static long gcd(long m, long n) {
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
