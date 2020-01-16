package solution;

/**
 * 我的日程安排表 I
 * 方法一：暴力法
 * 预订新的日程安排 [start, end) 时，检查当前每个日程安排是否与新日程安排冲突。若不冲突，则可以添加新的日程安排。
 * 算法：
 * 我们将维护一个日程安排列表（不一定要排序）。当且仅当其中一个日程安排在另一个日程安排结束后开始时，两个日程安排 [s1，e1) 和 [s2，e2) 不冲突：e1<=s2 或 e2<=s1。这意味着当 s1<e2 和 s2<e1 时，日程安排发生冲突。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/my-calendar-i/solution/wo-de-ri-cheng-an-pai-biao-i-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MyCalendar {
}
/**
 * 方法一：暴力法
 * 预订新的日程安排 [start, end) 时，检查当前每个日程安排是否与新日程安排冲突。若不冲突，则可以添加新的日程安排。
 *
 * 算法：
 * 我们将维护一个日程安排列表（不一定要排序）。当且仅当其中一个日程安排在另一个日程安排结束后开始时，两个日程安排 [s1，e1) 和 [s2，e2) 不冲突：e1<=s2 或 e2<=s1。这意味着当 s1<e2 和 s2<e1 时，日程安排发生冲突。
 * 复杂度分析
 *
 * 时间复杂度：O(N^2)O(N
 * 2
 *  )。NN 指的是日常安排的数量，对于每个新的日常安排，我们检查新的日常安排是否发生冲突来决定是否可以预订新的日常安排。所以时间复杂度为 \sum_k^N O(k) = O(N^2)∑
 * k
 * N
 * ​
 *  O(k)=O(N
 * 2
 *  )。
 * 空间复杂度：O(n)O(n)，calendar 所使用的空间大小。
 *
 * 方法二：平衡树
 * 如果我们按时间顺序维护日程安排，则可以通过二分查找日程安排的情况来检查新日常安排是否可以预订，时间复杂度为 O(\log N)O(logN) （其中 NN 是已预订的日常安排数），若可以预定则我们还需要在排序结构中插入日常安排。
 *
 * 算法：
 *
 * 我们需要一个数据结构能够保持元素排序和支持快速插入。在 java 中，TreeMap 是最适合的。在 python 中，我们可以构建自己的二叉树结构。
 *
 * 复杂度分析
 *
 * 时间复杂度 (Java)：O(N \log N)O(NlogN)。其中 NN 是预订的日程安排数。对于每个新日程安排，我们用 O(\log N)O(logN) 的时间搜索该日程安排是否合法，若合法则将其插入日常安排中需要 O(1)O(1) 的时间。
 * 时间复杂度 (Python)：最坏的情况 O(N^2)O(N
 * 2
 *  )，其他情况是 O(N \log N)O(NlogN)。对于每个新日程安排，若新日程安排合法则将新日程安排插入到二叉树中。由于此树可能不平衡，因此可能需要线性步骤来遍历每个日常安排。
 * 空间复杂度：O(N)O(N)，数据结构所使用的空间。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/my-calendar-i/solution/wo-de-ri-cheng-an-pai-biao-i-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */