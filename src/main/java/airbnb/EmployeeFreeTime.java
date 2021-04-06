package airbnb;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 759. 员工空闲时间
 * 给定员工的 schedule 列表，表示每个员工的工作时间。
 *
 * 每个员工都有一个非重叠的时间段  Intervals 列表，这些时间段已经排好序。
 *
 * 返回表示 所有 员工的 共同，正数长度的空闲时间 的有限时间段的列表，同样需要排好序。
 *
 * 示例 1：
 *
 * 输入：schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * 输出：[[3,4]]
 * 解释：
 * 共有 3 个员工，并且所有共同的
 * 空间时间段是 [-inf, 1], [3, 4], [10, inf]。
 * 我们去除所有包含 inf 的时间段，因为它们不是有限的时间段。
 *
 *
 * 示例 2：
 *
 * 输入：schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * 输出：[[5,6],[7,9]]
 *
 *
 * （尽管我们用 [x, y] 的形式表示 Intervals ，内部的对象是 Intervals 而不是列表或数组。例如，schedule[0][0].start = 1, schedule[0][0].end = 2，并且 schedule[0][0][0] 是未定义的）
 *
 * 而且，答案中不包含 [5, 5] ，因为长度为 0。
 *
 *
 *
 * 注：
 *
 * schedule 和 schedule[i] 为长度范围在 [1, 50]的列表。
 * 0 <= schedule[i].start < schedule[i].end <= 10^8。
 * 注：输入类型于 2019 年 4 月 15 日 改变。请重置为默认代码的定义以获取新方法。
 */
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new LinkedList<>();
        if(schedule == null || schedule.size() == 0) return result;

        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        for(List<Interval> e : schedule) {
            pq.addAll(e);
        }

        int latestEndTime = pq.poll().end;
        while(!pq.isEmpty()) {
            Interval curr = pq.poll();
            if(curr.start > latestEndTime) {
                result.add(new Interval(latestEndTime, curr.start));
                latestEndTime = curr.end;
            } else {
                latestEndTime = Math.max(latestEndTime, curr.end);
            }
        }
        return result;
    }
}
/**
 * 方法一
 * 排序只需要排starttime即可。只需要注意keep track of最晚的endtime
 *
 * 方法二
 * 方法一：事件（扫描线）[通过]
 * 如果某个区间与任一区间重叠，则该区间不会出现在答案中。所以我们可以将问题转换为：给定一组区间，找出所有员工都不包含的区间。
 *
 * 我们可以使用区间问题中的 “事件” 方法。对于每个区间 [s, e]，我们可以看作有两个事件：当 time = s 时，balance++；当 time = e 时，balance--。我们只关心 balance == 0 的区间。
 *
 * 算法：
 *
 * 对于每个区间，创建如上所述的两个事件，并对事件进行排序。在事件 t 发生的每个事件，如果 balance == 0，则说明 [prev，t] 是所有员工都不包含的区间，其中 prev 是 t 的前一个值。
 *
 * public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
 *         int OPEN = 0, CLOSE = 1;
 *
 *         List<int[]> events = new ArrayList();
 *         for (List<Interval> employee: avails)
 *             for (Interval iv: employee) {
 *                 events.add(new int[]{iv.start, OPEN});
 *                 events.add(new int[]{iv.end, CLOSE});
 *             }
 *
 *         Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
 *         List<Interval> ans = new ArrayList();
 *
 *         int prev = -1, bal = 0;
 *         for (int[] event: events) {
 *             // event[0] = time, event[1] = command
 *             if (bal == 0 && prev >= 0)
 *                 ans.add(new Interval(prev, event[0]));
 *             bal += event[1] == OPEN ? 1 : -1;
 *             prev = event[0];
 *         }
 *
 *         return ans;
 *     }
 *
 * 复杂度分析
 *
 * 时间复杂度：O(C\log C)O(ClogC)，其中 CC 是所有员工的区间数。
 * 空间复杂度：O(C)O(C)。
 */