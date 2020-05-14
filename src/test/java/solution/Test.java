package solution;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 1};
        int k = 1;
        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(arr, k)));
    }
}
