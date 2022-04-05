package airbnb;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 281. 锯齿迭代器
 * 给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。
 *
 * 示例:
 *
 * 输入:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 *
 * 输出: [1,3,2,4,5,6]
 *
 * 解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
 *      next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
 * 拓展：假如给你 k 个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?
 *
 * 拓展声明：
 *  “锯齿” 顺序对于 k > 2 的情况定义可能会有些歧义。所以，假如你觉得 “锯齿” 这个表述不妥，也可以认为这是一种 “循环”。例如：
 *
 * 输入:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 *
 * 输出: [1,4,8,2,5,9,3,6,7].
 */

public class ZigZagIterator {
    Queue<Iterator<Integer>> queue = new LinkedList<>();
    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        Iterator<Integer> it1 = v1.iterator();
        Iterator<Integer> it2 = v2.iterator();
        if (it1.hasNext()) {
            queue.add(it1);
        }
        if (it2.hasNext()) {
            queue.add(it2);
        }
    }

    public int next() {
        Iterator<Integer> it = queue.poll();
        int v = it.next();
        if (it.hasNext()) {
            queue.add(it);
        }
        return v;
    }

    public boolean hasNext() {
        if (!queue.isEmpty()) {
            return true;
        }
        return false;
    }
}