package airbnb;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 642. 设计搜索自动补全系统
 * 为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。除 '#' 以外用户输入的每个字符，返回历史中热度前三并以当前输入部分为前缀的句子。下面是详细规则：
 *
 * 一条句子的热度定义为历史上用户输入这个句子的总次数。
 * 返回前三的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
 * 如果满足条件的句子个数少于 3，将它们全部输出。
 * 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
 * 你的工作是实现以下功能：
 *
 * 构造函数：
 *
 * AutocompleteSystem(String[] sentences, int[] times): 这是构造函数，输入的是历史数据。 Sentences 是之前输入过的所有句子，Times 是每条句子输入的次数，你的系统需要记录这些历史信息。
 *
 * 现在，用户输入一条新的句子，下面的函数会提供用户输入的下一个字符：
 *
 * List<String> input(char c): 其中 c 是用户输入的下一个字符。字符只会是小写英文字母（'a' 到 'z' ），空格（' '）和特殊字符（'#'）。输出历史热度前三的具有相同前缀的句子。
 *
 *
 *
 * 样例 ：
 * 操作 ： AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * 系统记录下所有的句子和出现的次数：
 * "i love you" : 5 次
 * "island" : 3 次
 * "ironman" : 2 次
 * "i love leetcode" : 2 次
 * 现在，用户开始新的键入：
 *
 *
 * 输入 ： input('i')
 * 输出 ： ["i love you", "island","i love leetcode"]
 * 解释 ：
 * 有四个句子含有前缀 "i"。其中 "ironman" 和 "i love leetcode" 有相同的热度，由于 ' ' 的 ASCII 码是 32 而 'r' 的 ASCII 码是 114，所以 "i love leetcode" 在 "ironman" 前面。同时我们只输出前三的句子，所以 "ironman" 被舍弃。
 *
 * 输入 ： input(' ')
 * 输出 ： ["i love you","i love leetcode"]
 * 解释:
 * 只有两个句子含有前缀 "i "。
 *
 * 输入 ： input('a')
 * 输出 ： []
 * 解释 ：
 * 没有句子有前缀 "i a"。
 *
 * 输入 ： input('#')
 * 输出 ： []
 * 解释 ：
 *
 * 用户输入结束，"i a" 被存到系统中，后面的输入被认为是下一次搜索。
 *
 *
 *
 * 注释 ：
 *
 * 输入的句子以字母开头，以 '#' 结尾，两个字母之间最多只会出现一个空格。
 * 即将搜索的句子总数不会超过 100。每条句子的长度（包括已经搜索的和即将搜索的）也不会超过 100。
 * 即使只有一个字母，输出的时候请使用双引号而不是单引号。
 * 请记住清零 AutocompleteSystem 类中的变量，因为静态变量、类变量会在多组测试数据中保存之前结果。详情请看这里。
 */
@Component
class AutocompleteSystem {
    // 字典树
    private final Trie root;
    // 记录前一结点
    private Trie pre;
    // 记录历史字符串
    private StringBuffer sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new Trie(3);
        this.pre = root;
        sb = new StringBuffer();

        for (int i = 0; i < sentences.length; i++) {
            root.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        // 终止
        if (c == '#') {
            // 更新历史记录
            saveHistory(sb.toString());
            // 清空状态
            pre = root;
            sb = new StringBuffer();
            return res;
        }

        // 记录历史记录
        sb.append(c);

        // 更新结点
        if (pre != null) pre = c == ' ' ? pre.children[26] : pre.children[c - 'a'];
        // 搜索其后所有值
        if (pre != null) pre.search(res);

        return res;
    }

    private void saveHistory(String s) {
        root.insert(s, 1);
    }
}

class Trie {
    // 只有结尾结点才有的属性
    boolean isEnding;
    int count;
    String s;

    Trie[] children = new Trie[27];
    // 最小堆保存最大的k个
    int k;
    PriorityQueue<Trie> queue;

    Trie(int k) {
        this.k = k;
        // 最小堆
        this.queue = new PriorityQueue<>((a, b) -> a.count == b.count ? b.s.compareTo(a.s) : a.count - b.count);
        // 最后一位存放空格
        this.children = new Trie[27];
    }

    public void insert(String word, int val) {
        if (word == null || word.isEmpty()) return;

        Trie temp = this;
        // 记录路径上的结点
        List<Trie> path = new LinkedList<>();
        for (char c : word.toCharArray()) {
            int idx = c == ' ' ? 26 : (c - 'a');
            if (temp.children[idx] == null) temp.children[idx] = new Trie(k);

            temp = temp.children[idx];
            path.add(temp);
        }
        // 结尾字符标记及计数更新
        temp.isEnding = true;
        temp.count += val;
        temp.s = word;

        // 关联终止节点到路径上每个结点
        for (Trie cur : path) {
            // 移除老的值
            if (cur.queue.contains(temp)) cur.queue.remove(temp);
            // 加入新的值
            cur.queue.add(temp);
            // 大于k个，将最小的弹出
            while (cur.queue.size() > k) cur.queue.poll();
        }
    }

    public void search(List<String> res) {
        List<Trie> temp = new ArrayList<>();
        // 加入堆中元素
        while (!queue.isEmpty()) {
            Trie trie = queue.poll();
            temp.add(trie);
            res.add(trie.s);
        }
        queue.addAll(temp);
        Collections.reverse(res);
    }
}

/**
 * 主题思路为将整个句子都视作单词加入字典树。但前缀匹配存在回溯较多的情况，比较消耗时间性能；修改字典树结点结构，使得每个结点维护其后计数最大的三个字符串，加速匹配。
 *
 * 执行用时 :146 ms, 在所有 Java 提交中击败了99.38%的用户
 * 内存消耗 :49 MB, 在所有 Java 提交中击败了100.00%的用户
 *
 * 作者：mazw-2
 * 链接：https://leetcode-cn.com/problems/design-search-autocomplete-system/solution/zi-dian-shu-by-mazw-2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */