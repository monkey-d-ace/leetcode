package solution;

/**
 * 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianofTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3};
        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m  == 0) {
            if (n % 2 != 0) {
                return 1.0 * nums2[n/2];
            } else {
                return (nums2[n/2] + nums2[n/2-1])/2.0;
            }
        }
        if (n == 0) {
            if (m % 2 != 0) {
                return 1.0 * nums1[m/2];
            } else {
                return (nums1[n/2] + nums1[n/2-1])/2.0;
            }
        }
        int total = m + n;
        if ((total & 1) == 1) {
            return find_kth(nums1, 0, nums2, 0, total / 2 + 1);
        }
        //总数为偶数，找第 total / 2 个数和第total / 2 + 1个数的平均值
        return (find_kth(nums1, 0, nums2, 0, total / 2) + find_kth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
    }

    private static double find_kth(int[] nums1, int nums1_begin, int[] nums2, int nums2_begin, int k) {
        if (nums1_begin >= nums1.length) {
            return nums2[nums2_begin + k - 1];
        }
        if (nums2_begin >= nums2.length) {
            return nums1[nums1_begin + k - 1];
        }
        if (k == 1) {
            if (nums1[nums1_begin] > nums2[nums2_begin]) {
                return nums1[nums1_begin];
            } else {
                return nums2[nums2_begin];
            }
        }
        int mid_a = Integer.MAX_VALUE;
        int mid_b = Integer.MAX_VALUE;
        if (nums1_begin + k/2 -1 < nums1.length) {
            mid_a = nums1[nums1_begin + k/2 - 1];
        }
        if (nums2_begin + k/2 - 1 < nums2.length) {
            mid_b = nums2[nums2_begin + k/2 - 1];
        }
        if (mid_a < mid_b) {
            return find_kth(nums1, nums1_begin + k/2, nums2, nums2_begin + k/2, k - k/2);
        } else {
            return find_kth(nums1, nums1_begin, nums2, nums2_begin + k/2, k -k/2 );
        }
    }
}
