package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 528. 按权重随机选择
 *
 * 给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 *
 * 说明:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex 将被调用不超过 10000 次
 * 示例1:
 *
 * 输入:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出: [null,0]
 * 示例2:
 *
 * 输入:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出: [null,0,1,1,1,0]
 * 输入语法说明：
 *
 * 输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有一个参数，即数组 w。pickIndex 没有参数。输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。
 */
public class RandomPickWithWeight {
    List<Integer> psum = new ArrayList<>();
    int tot = 0;
    Random rand = new Random();

    public RandomPickWithWeight(int[] w) {
        for (int x : w) {
            tot += x;
            psum.add(tot);
        }
    }

    public int pickIndex() {
        int targ = rand.nextInt(tot);
        int lo = 0;
        int hi = psum.size() - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= psum.get(mid)) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
/**
 * 方法 1：前缀和与二分搜索
 * 想法
 *
 * 让 \text{tot} = \sum_{i=0}^{N-1}w[i]tot=∑
 * i=0
 * N−1
 * ​
 *  w[i] ，其中 N = \text{len}(w)N=len(w)。
 *
 * 如果我们从 半开区间 [0, \text{tot})[0,tot) 中随机选择一个整数会发生什么？
 *
 * 是否有办法将每一个可能的整数映射到 ww 中一个下标，使得每个下标映射的数目与下标的权重对应呢？
 *
 * 是否有办法使用少于 O(\text{tot})O(tot) 的空间呢？
 *
 * 算法
 *
 * 求出前缀和数组 pp，其中 p[x] = \sum_{i=0}^{x}w[i]p[x]=∑
 * i=0
 * x
 * ​
 *  w[i]。
 *
 * 在范围 [0,tot) 中随机选择一个整数 targ。
 *
 * 使用二分查找来找到下标 x，其中 xx 是满足 \text{targ} < p[x]targ<p[x] 的最小下标。
 *
 * 对于某些下标 i，所有满足 p[i] - w[i] \leq v < p[i]p[i]−w[i]≤v<p[i] 的整数 vv 都映射到这个下标。因此，所有的下标都与下标权重成比例。
 *
 *
 * Spring Cloud Ribbon （客户端负载均衡）策略中的 WeightedResponseTimeRule
 * 此题可简述为「按权重，看作多个区间，按区间宽度越大，概率越大」
 * 在 Ribbon 相关架构中，服务端给客户端一个服务列表，类似 Map<String, Set<String>> 结构。若客户端想调用 key = serviceA，可选的具体服务端实例有 Set<String> 的 ["/svc/a1", "/svc/a2", "/svc/a3"]，由客户端自行决定
 * Ribbon 作为客户端负载均衡来帮助客户端选择去哪个具体服务实例（a1 / a2 / a3），希望雨露均沾，又希望别运气不好抽到响应慢的服务器，故有了一种根据权重的均衡策略
 * 权重是通过定时统计最近一段时间内，a1 / a2 / a3 各自的访问响应时间如何，如 a1: 10ms，a2: 20ms，a3: 40ms
 * 通过算法（不赘述，有兴趣可留言喔）计算得 a1: [0, 60]，a2: (60, 110]，a3: (110, 140] 的区间对应
 * 下次再需要访问 serviceA 时，随机一个数 [0, 140]，看落在哪个区间，就选那个实例
 * RabbitMQ 的 Topic 交换器使用 Trie 匹配
 * MySQL 中的 IN 语法涉及二分算法
 */