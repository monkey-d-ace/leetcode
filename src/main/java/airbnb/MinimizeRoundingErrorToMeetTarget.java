package airbnb;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1058. 最小化舍入误差以满足目标
 * 给定一系列价格 [p1,p2...,pn] 和一个目标 target，将每个价格 pi 舍入为 Roundi(pi) 以使得舍入数组 [Round1(p1),Round2(p2)...,Roundn(pn)] 之和达到给定的目标值 target。每次舍入操作 Roundi(pi) 可以是向下舍 Floor(pi) 也可以是向上入 Ceil(pi)。
 *
 * 如果舍入数组之和无论如何都无法达到目标值 target，就返回 -1。否则，以保留到小数点后三位的字符串格式返回最小的舍入误差，其定义为 Σ |Roundi(pi) - (pi)|（ i 从 1 到 n ）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：prices = ["0.700","2.800","4.900"], target = 8
 * 输出："1.000"
 * 解释：
 * 使用 Floor，Ceil 和 Ceil 操作得到 (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 。
 * 示例 2：
 *
 * 输入：prices = ["1.500","2.500","3.500"], target = 10
 * 输出："-1"
 * 解释：
 * 达到目标是不可能的。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 500
 * 表示价格的每个字符串 prices[i] 都代表一个介于 0 和 1000 之间的实数，并且正好有 3 个小数位。
 * target 介于 0 和 1000000 之间。
 */
@Component
public class MinimizeRoundingErrorToMeetTarget {
    public String minimizeError(String[] prices, int target) {
        int N = prices.length;
        Double[] p = new Double[N];
        int sum = 0;
        int count = 0;
        for(int i = 0; i < N; ++i) {
            p[i] = Double.valueOf(prices[i]);
            int f =(int)Math.floor(p[i]);
            if(f != p[i])
                ++count;
            sum += f;
        }
        if(target < sum || target > sum + count)
            return "-1";

        count = target - (int)sum;
        Arrays.sort(p, new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                return o1 - Math.floor(o1) > o2 - Math.floor(o2) ? -1 : 1;
            }
        });
        double ans = 0;
        for(int i = 0; i < N; ++i) {
            if(i < count)
                ans += Math.ceil(p[i]) - p[i];
            else
                ans += p[i] - Math.floor(p[i]);
        }
        return String.format("%.3f", ans);
    }
}
/**
 *
 */