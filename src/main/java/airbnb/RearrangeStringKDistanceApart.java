package airbnb;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 358. K 距离间隔重排字符串
 * 给你一个非空的字符串 s 和一个整数 k，你要将这个字符串中的字母进行重新排列，使得重排后的字符串中相同字母的位置间隔距离至少为 k。
 *
 * 所有输入的字符串都由小写字母组成，如果找不到距离至少为 k 的重排结果，请返回一个空字符串 ""。
 *
 * 示例 1：
 *
 * 输入: s = "aabbcc", k = 3
 * 输出: "abcabc"
 * 解释: 相同的字母在新的字符串中间隔至少 3 个单位距离。
 * 示例 2:
 *
 * 输入: s = "aaabc", k = 3
 * 输出: ""
 * 解释: 没有办法找到可能的重排结果。
 * 示例 3:
 *
 * 输入: s = "aaadbbcc", k = 2
 * 输出: "abacabcd"
 * 解释: 相同的字母在新的字符串中间隔至少 2 个单位距离。
 */
@Component
public class RearrangeStringKDistanceApart {
    public String rearrangeString(String s, int k) {
        if (k <= 1) {
            return s;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        // 大顶堆
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Character c : s.toCharArray()) {
            // 遍历字符，统计字符的出现次数
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        maxHeap.addAll(map.entrySet()); // 装入大顶堆，按照字符重复次数作为比较
        StringBuilder sb = new StringBuilder(s.length());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();    // 从大顶堆取出重复次数最多的字符
            sb.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1); // 用掉一个字符，次数减一
            queue.offer(currentEntry);  // 放入到queue中，因为k距离后还要用。
            if (queue.size() == k) {
                // queue的大小到达了k，也就是说我们已经越过了k个单位，在结果中应该要出现相同的字母了
                Map.Entry<Character, Integer> entry = queue.poll();
                if (entry.getValue() > 0) {
                    // 该字符的重复次数大于 0，则添加入大顶堆中，要是0那还加它干嘛
                    maxHeap.add(entry);
                }
            }
        }
        // 退出 while 循环就是大顶堆已经没有元素了，如果此时 sb 的长度没有还原，说明还有元素挂在 queue 中
        // 即 queue.size() == k 这个条件没有完全满足，即存在一些字符无法间隔 k 个距离
        return sb.length() == s.length() ? sb.toString() : "";


    }
}
/**
 * 与Java "Top" k - 7不同的是，之前是只要相同的字母不挨着就🉑️，这里是定义了一个距离，那我们就多加一个判断距离的量就好了呀！这里我们用到了一个queue来存储未到达指定距离前的字母。
 *
 * 思路：
 *
 * 老方法，创建map，创建maxheap来按照freq存字母，创建stringbuilder，不要用string。
 * 提取出当前堆顶的健值对，加入到结果，自己的值-1，然后放入到queue中，因为k距离后还要用。
 * 如果queue的大小到达了k，也就是说我们已经越过了k个单位，在结果中应该要出现相同的字母了，所以我们就取出queue堆顶加入到maxheap中（前提是取出的健值对是有值的哈，要是0那还加它干嘛。。。而且queue存的时候就是按照顺序的）。
 * 重复以上步骤，最后判断一下得到的结果的长度和之前的s就可以了！
 *
 * time：O(n) = n * log(n)，n为char的个数。
 * space：O(n) = n。
 */