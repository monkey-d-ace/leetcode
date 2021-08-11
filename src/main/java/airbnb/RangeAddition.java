package airbnb;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 370. 区间加法
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k​​​​​​​ 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 * 示例:
 *
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 * 解释:
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 */
@Component
public class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        Arrays.fill(res, 0);
        if(updates.length == 0) return res;
        for(int[] update : updates) {
            res[update[0]] += update[2];
            if(update[1] < length - 1) {
                res[update[1] + 1] -= update[2];
            }
        }
        for(int i = 1; i < res.length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
/**
 * 方法一：差分数组
 * 如果我们知道每一个元素比前一个元素大多少，以及第一元素的值，我们就可以 O(n)O(n) 遍历数组求出所有数的值。举个例子，我们已知 a_1=3a
 * 1
 * ​
 *  =3 ，a_2a
 * 2
 * ​
 *   比 a_1a
 * 1
 * ​
 *   大 5，则我们可以知道 a_2=a_1+5=8a
 * 2
 * ​
 *  =a
 * 1
 * ​
 *  +5=8 。
 *
 * 回到题目本身，每个操作其实代表了这样两个事情：
 *
 * a_{startIndex}a
 * startIndex
 * ​
 *   比前一个元素多了 incinc 。
 * a_{endIndex + 1}a
 * endIndex+1
 * ​
 *   比前一个元素少了 incinc 。
 * 则我们可以建立一个差分数组 bb 表示元素间的差值，即 b_i=a_i-a_{i-1}b
 * i
 * ​
 *  =a
 * i
 * ​
 *  −a
 * i−1
 * ​
 *   ，则刚刚的操作就等价于：
 *
 * b_{startIndex} += incb
 * startIndex
 * ​
 *  +=inc 。
 * b_{endIndex+1} -= incb
 * endIndex+1
 * ​
 *  −=inc 。
 * 最后我们用 bb 数组求出 aa 数组，即为我们要的答案。
 *
 * C++
 *
 * class Solution {
 * public:
 *     vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
 *         vector<int> vec(length,0);
 *         for (auto x:updates){
 *             vec[x[0]]+=x[2];
 *             if (x[1]+1<length) vec[x[1]+1]-=x[2];
 *         }
 *         for (int i=1;i<length;++i) vec[i]+=vec[i-1];
 *         return vec;
 *     }
 * };
 * 复杂度分析
 *
 * 时间复杂度：O(k+n)O(k+n) ，每个操作值修改两个元素，即修改操作是 O(1)O(1) 的，最后遍历差分数组是 O(n)O(n) 的。
 * 空间复杂度：O(n)O(n) 。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/range-addition/solution/qu-jian-jia-fa-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */