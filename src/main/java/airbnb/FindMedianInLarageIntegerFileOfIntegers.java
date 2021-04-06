package airbnb;

/**
 * Find the median from a large file of integers. You can not access the numbers by index, can only access it sequentially. And the numbers cannot fit in memory.
 */
public class FindMedianInLarageIntegerFileOfIntegers {
    public static void main(String[] args) {
        FindMedianInLarageIntegerFileOfIntegers inLarageIntegerFileOfIntegers = new FindMedianInLarageIntegerFileOfIntegers();
        System.out.println(inLarageIntegerFileOfIntegers.findMedian(new int[]{3, -2, 7}));
        System.out.println(inLarageIntegerFileOfIntegers.findMedian(new int[] {-100, 99, 3, 0, 5, 7, 11, 66, -33}));
        System.out.println(inLarageIntegerFileOfIntegers.findMedian(new int[]{4, -100, 99, 3, 0, 5, 7, 11, 66, -33}));
    }
    private long search(int[] nums, int k, long left, long right) {
        if (left >= right) {
            return left;
        }

        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                res = Math.max(res, num);
            }
        }

        if (count == k) {
            return res;
        } else if (count < k) {
            return search(nums, k, Math.max(res + 1, guess), right);
        } else {
            return search(nums, k, left, res);
        }
    }

    public double findMedian(int[] nums) {
        int len = 0;
        for (int num : nums) {
            len++;
        }

        if (len % 2 == 1) {
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                    search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
        }
    }
}
