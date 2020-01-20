package solution;

import java.util.TreeMap;

/**
 * 敲击计数器
 *
 * 设计一个敲击计数器，使它可以统计在过去5分钟内被敲击次数。
 *
 * 每个函数会接收一个时间戳参数（以秒为单位），你可以假设最早的时间戳从1开始，且都是按照时间顺序对系统进行调用（即时间戳是单调递增）。
 *
 * 在同一时刻有可能会有多次敲击。
 *
 * 示例:
 *
 * HitCounter counter = new HitCounter();
 *
 * // 在时刻 1 敲击一次。
 * counter.hit(1);
 *
 * // 在时刻 2 敲击一次。
 * counter.hit(2);
 *
 * // 在时刻 3 敲击一次。
 * counter.hit(3);
 *
 * // 在时刻 4 统计过去 5 分钟内的敲击次数, 函数返回 3 。
 * counter.getHits(4);
 *
 * // 在时刻 300 敲击一次。
 * counter.hit(300);
 *
 * // 在时刻 300 统计过去 5 分钟内的敲击次数，函数返回 4 。
 * counter.getHits(300);
 *
 * // 在时刻 301 统计过去 5 分钟内的敲击次数，函数返回 3 。
 * counter.getHits(301);
 *
 * 进阶:
 *
 * 如果每秒的敲击次数是一个很大的数字，你的计数器可以应对吗？
 */
public class HitCounter {
    private int lastHit;
    private TreeMap<Integer, Integer> treeMap;
    public HitCounter() {
        this.lastHit = 0;
        this.treeMap = new TreeMap<Integer, Integer>();
    }
    public void hit(int timestamp) {
        if (lastHit == 0) {
            treeMap.put(timestamp, 1);
        } else {
            treeMap.put(timestamp, treeMap.get(lastHit) + 1);
        }
        lastHit = timestamp;
    }

    public int getHits(int timestamp) {
        if (lastHit == 0) {
            return 0;
        } else {
            if (timestamp - 300 <= 0) {
                return treeMap.get(lastHit);
            } else {
                Integer key = treeMap.floorKey(timestamp - 300);
                if (key == null) {
                    return treeMap.get(lastHit);
                } else {
                    return treeMap.get(lastHit) - treeMap.get(key);
                }
            }
        }
    }
}
