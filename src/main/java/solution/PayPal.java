package solution;

import java.util.ArrayList;
import java.util.List;

public class PayPal {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        PayPal payPal = new PayPal();
        System.out.println(payPal.print(matrix));
    }

    public List<Integer> print(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return ans;
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                ans.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                ans.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    ans.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    ans.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }
}
