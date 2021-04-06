package airbnb;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * 251. 展开二维向量
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。
 *
 *
 *
 * 示例：
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *
 *
 * 注意：
 *
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *
 *
 * 进阶：尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器
 */
@Component
public class Vector2D {
    private int[][] vector;
    private int inner = 0;
    private int outer = 0;

    public Vector2D(int[][] vec) {
        // We need to store a *reference* to the input vector.
        vector = vec;
    }

    // If the current outer and inner point to an integer, this method does nothing.
    // Otherwise, inner and outer are advanced until they point to an integer.
    // If there are no more integers, then outer will be equal to vector.length
    // when this method terminates.
    private void advanceToNext() {
        // While outer is still within the vector, but inner is over the
        // end of the inner list pointed to by outer, we want to move
        // forward to the start of the next inner vector.
        while (outer < vector.length && inner == vector[outer].length) {
            inner = 0;
            outer++;
        }
    }

    public int next() {
        // As per Java specs, throw an exception if there's no next.
        // This will also ensure the pointers point to an integer otherwise.
        if (!hasNext()) throw new NoSuchElementException();
        // Return current element and move inner so that is after the current
        // element.
        return vector[outer][inner++];
    }

    public boolean hasNext() {
        // Ensure the position pointers are moved such they point to an integer,
        // or put outer = vector.length.
        advanceToNext();
        // If outer = vector.length then there are no integers left, otherwise
        // we've stopped at an integer and so there's an integer left.
        return outer < vector.length;
    }
}
/**
 *
 * 方法二：双指针
 * 正如上面所说，方法一是不好的，因为它创建一了一个新的数据结构，而不是简单的遍历给定数据结构。相反，我们应该找到一种方法，一步步的遍历整数，跟踪我们当前在二维向量中的位置。每个数字的位置用两个索引表示：内部向量的索引和内部向量内整数的索引。下面是一个例子，上面标记了索引。
 *
 *
 *
 * 假设我们处于以下位置：
 *
 *
 *
 * 我们如何找到下一个位置？现在的整数后面还有一个整数，在同一个向量里。因此我们可以增加 inner 索引。这将得到下一个位置，如下所示。
 *
 *
 *
 * 现在 inner 指向当前向量的末尾。为了得到下一个整数，我们需要将 outer 增加 1，并将 inner 设置为 0。因为 0 是新向量的第一个索引。
 *
 *
 *
 * 这一次我们想要获得下一个整数，我们需要跳过空向量。为此，我们反复增加 outer，直到找到一个不为空的内部向量。
 *
 *
 *
 * 当 outer 等于 2D 向量的长度时，这意味着没有更多的内部向量了，因此也就没有更多的数字了。
 *
 * 算法：
 *
 * 在方法一中，我们的构造函数使用 O(N)O(N) 的辅助空间和 O(N + V)O(N+V) 的时间。不过，在这种方法中，我们在调用 hasNext() 和 next() 期间增量的执行必要的工作。这意味着，如果调用方在迭代器耗尽之前停止使用，就不会做任何多余的工作。
 *
 * 我们将定义一个 advanceToNext() 的辅助方法，该方法检查当前 inner 和 outer 是否指向整数，如果不指向整数，则它们将继续向前移动，直到它们指向整数为止。如果 outer == vector.length 为真，则方法终止，因为没有多余的整数了。
 *
 * 为了确保不做不必要的工作，构造函数检查 vector[0][0] 是否指向整数。这是因为输入向量的开头有可能有任意数量的空向量，跳过这些空向量可能需要 O(V)O(V) 的操作。
 *
 * hasNext() 和 next() 都从调用 advanceToNext() 开始，以确保 inner 和 outer 指向整数，或者 outer 处于停止值 outer = vector.length。
 *
 * next() 返回 vector[inner][outer] 处的整数，然后将 inner 增加 1，以便下次调用 advanceToNext() 时从返回整数的下一个整数开始搜索。
 *
 * 需要注意的是，调用 hasNext() 方法只会导致指针在不指向整数时移动。一旦它们指向整数，对 hasNext() 的重复调用将不会有进一步移动。只有 next() 会将它们移动指向无效的位置。这种设计确保多次调用 hasNext() 都不会产生异常。
 *
 *
 * 复杂度分析
 *
 * 设 NN 为 2D 向量内的整数个数，VV 为内部向量个数。
 *
 * 时间复杂度：
 * 构造函数：O(1)O(1)。我们只存储了对输入向量的引用是 O(1)O(1) 操作。
 * advanceToNext()：O(\dfrac{V}{N})O(
 * N
 * V
 * ​
 *  )。如果迭代器耗尽，那么总共最多调用 advanceToNext() 将执行 O(N + V)O(N+V) 的操作。但是我们最少可以调用 N 次 advanceToNext() 操作以耗尽迭代器。因此该操作的摊还复杂度为 \dfrac{O(N + V)}{N} = O(\dfrac{N}{N} + \dfrac{V}{N}) = O(\dfrac{V}{N})
 * N
 * O(N+V)
 * ​
 *  =O(
 * N
 * N
 * ​
 *  +
 * N
 * V
 * ​
 *  )=O(
 * N
 * V
 * ​
 *  )。
 * next()/hasNext()：O(\dfrac{V}{N})O(
 * N
 * V
 * ​
 *  ) 和 O(1)O(1)。这两个方法的成本取决于如何调用它们。如果我们刚从 next() 获得一个值，那么这两个方法的下一次调用就会调用 advanceToNext()。在本例中，时间复杂度为 O(\dfrac{V}{N})O(
 * N
 * V
 * ​
 *  )。
 * 但是，如果我们调用 hasNext()，那么对 hasNext() 的所有连续调用或对 next() 的下一个调用都将是 O(1)O(1)。这是因为 advanceToNext() 只执行 O(1)O(1) 的检查并立即返回。
 * 空间复杂度：O(1)O(1)，我们的向量是一个引用而不是副本，所以是 O(1)O(1)。
 *
 * 作者：LeetCode
 * 链接：https://leetcode-cn.com/problems/flatten-2d-vector/solution/zhan-kai-er-wei-xiang-liang-by-leetcode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
