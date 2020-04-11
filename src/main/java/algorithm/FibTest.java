package algorithm;

public class FibTest {
    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println((int)'0' * (int)'2');
    }

    public static int fib(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            int a = fib(0), b = fib(1);
            for (int i = 2; i <= N; i++) {
                int temp = a;
                a = b;
                b = temp + b;
                System.out.println(a);
            }
            return b;
        }
    }
}
