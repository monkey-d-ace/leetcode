package airbnb;

/**
 * Given a set of numbers in an array which represent number of consecutive nights of AirBnB reservation requested, as a host, pick the sequence which maximizes the number of days of occupancy, at the same time, leaving at least 1 day gap in between bookings for cleaning. Problem reduces to finding max-sum of non-consecutive array elements.
 */
public class MaximumNumberOfNightsYouCanAccommodate {
    public static void main(String[] args) {
        MaximumNumberOfNightsYouCanAccommodate max = new MaximumNumberOfNightsYouCanAccommodate();
        int[] test1 = {5, 6, 3, 1};
        System.out.println(max.rob(test1));
        int[] test2 = {6, 5, 0, 1, 0, 9};
        System.out.println(max.rob(test2));

        int[] test3 = {5, 1, 1, 5};
        System.out.println(max.rob(test3));
        int[] test4 = {3, 6, 4};
        System.out.println(max.rob(test4));

        int[] test5 = {4, 10, 3, 1, 5};
        System.out.println(max.rob(test5));
    }
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int f1 = nums[0]; // max sof far, excluding current
        int f2 = Math.max(nums[0], nums[1]); // max so far
        for (int i = 2; i < n; i++) {
            int f = Math.max(f1 + nums[i], f2);
            f1 = f2;
            f2 = f;
        }

        return f2;
    }
}
