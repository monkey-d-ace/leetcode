package airbnb;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 1257. 最小公共区域
 * 给你一些区域列表 regions ，每个列表的第一个区域都包含这个列表内所有其他区域。
 *
 * 很自然地，如果区域 X 包含区域 Y ，那么区域 X  比区域 Y 大。
 *
 * 给定两个区域 region1 和 region2 ，找到同时包含这两个区域的 最小 区域。
 *
 * 如果区域列表中 r1 包含 r2 和 r3 ，那么数据保证 r2 不会包含 r3 。
 *
 * 数据同样保证最小公共区域一定存在。
 *
 *
 *
 * 示例 1：
 *
 * 输入：
 * regions = [["Earth","North America","South America"],
 * ["North America","United States","Canada"],
 * ["United States","New York","Boston"],
 * ["Canada","Ontario","Quebec"],
 * ["South America","Brazil"]],
 * region1 = "Quebec",
 * region2 = "New York"
 * 输出："North America"
 *
 *
 * 提示：
 *
 * 2 <= regions.length <= 10^4
 * region1 != region2
 * 所有字符串只包含英文字母和空格，且最多只有 20 个字母。
 */
@Component
public class SmallestCommonRegion {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> map = new HashMap<>();
        for (List<String> lst : regions) {
            Iterator<String> iter = lst.iterator();
            String parent = iter.next();
            while (iter.hasNext()) {
                map.put(iter.next(), parent);
            }
        }
        ArrayList<String> path1 = getPath(region1, map);
        ArrayList<String> path2 = getPath(region2, map);
        int i = path1.size() - 1, j = path2.size() - 1;
        while (i >= 0 && j >= 0 && path1.get(i).equals(path2.get(j))) {
            i--;
            j--;
        }
        return path1.get(i+1);
    }

    public ArrayList<String> getPath(String leaf, Map<String, String> map) {
        ArrayList<String> res = new ArrayList<>();
        while (map.containsKey(leaf)) {
            res.add(leaf);
            leaf = map.get(leaf);
        }
        res.add(leaf);
        return res;
    }
}
/**
 * 解题思路
 * 使用一个HashMap记录节点的parent，整个HashMap可以做一个有向图。从region1开始，构建一个到最大区域的路径。
 * 这样我们就可以从两个path的末尾开始比较，知道遇到第一个不同的节点。
 *
 * 复杂度分析
 * 时间复杂度：O(n)O(n)
 * 空间复杂度：O(n)O(n)
 *
 * 作者：MAGI003769
 * 链接：https://leetcode-cn.com/problems/smallest-common-region/solution/javati-jie-ben-zhi-shi-zui-di-gong-gong-ruhfq/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */