package airbnb;

import org.springframework.stereotype.Component;

/**
 * 280. 摆动排序
 * 给你一个无序的数组 nums, 将该数字 原地 重排后使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。
 *
 * 示例:
 *
 * 输入: nums = [3,5,2,1,6,4]
 * 输出: 一个可能的解答是 [3,5,1,6,2,4]
 */
@Component
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            less = !less;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
/**
 * 方法二：一遍交换 【通过】
 * 直觉告诉我吗，可以只用一遍完成任务。当我们遍历整个数组，比较当前元素与下一个元素。若顺序不正确，则交换之。
 * 复杂度分析
 *
 * 时间复杂度 : O(n)O(n)。
 * 在最坏的情况下，我们最多交换了n \over 2
 * 2
 * n
 * ​
 *   次。例如输入为 [2,1,3,1,4,1]。
 *
 * 空间复杂度 : O(1)O(1)。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/wiggle-sort/solution/bai-dong-pai-xu-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */