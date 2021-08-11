package airbnb;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 364. 加权嵌套序列和 II
 * 给一个嵌套整数序列，请你返回每个数字在序列中的加权和，它们的权重由它们的深度决定。
 *
 * 序列中的每一个元素要么是一个整数，要么是一个序列（这个序列中的每个元素也同样是整数或序列）。
 *
 * 与 前一个问题 不同的是，前一题的权重按照从根到叶逐一增加，而本题的权重从叶到根逐一增加。
 *
 * 也就是说，在本题中，叶子的权重为1，而根拥有最大的权重。
 *
 * 示例 1:
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: 8
 * 解释: 四个 1 在深度为 1 的位置， 一个 2 在深度为 2 的位置。
 * 示例 2:
 *
 * 输入: [1,[4,[6]]]
 * 输出: 17
 * 解释: 一个 1 在深度为 3 的位置， 一个 4 在深度为 2 的位置，一个 6 在深度为 1 的位置。 1*3 + 4*2 + 6*1 = 17。
 */
public class NestedListWeightSum2 {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int result = 0;
        if(nestedList == null || nestedList.size() == 0){
            return result;
        }
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
        Stack<Integer> levelSum = new Stack<Integer>();
        for(int i = 0; i < nestedList.size(); i++){
            queue.offer(nestedList.get(i));
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            int eachLevel = 0;
            for(int i = 0; i < size; i++){
                NestedInteger temp = queue.poll();
                if(temp.isInteger()){
                    eachLevel += temp.getInteger();
                }else{
                    for(NestedInteger one : temp.getList()){
                        queue.offer(one);
                    }
                }
            }
            levelSum.push(eachLevel);
        }
        int n = 1;
        while(!levelSum.isEmpty()){
            result += n * levelSum.pop();
            n++;
        }
        return result;
    }
}
/**
 * 1、BFS搜索过程中求出每一层的和
 * 2、按层压栈
 * 3、出栈时把每层和乘以权重求总和
 *
 */