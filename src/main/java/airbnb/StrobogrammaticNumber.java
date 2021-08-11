package airbnb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 246. 中心对称数
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num = "69"
 * 输出: true
 * 示例 2:
 *
 * 输入: num = "88"
 * 输出: true
 * 示例 3:
 *
 * 输入: num = "962"
 * 输出: false
 * 示例 4：
 *
 * 输入：num = "1"
 * 输出：true
 * 通过次数4,322提交次数9,211
 * 在真实的面试中遇到过这道题？
 */
@Component
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');  //满足要求的数字对
        int left = 0, right = num.length() - 1;     //双指针初始化
        while(left <= right){
            if(map.get(num.charAt(left)) == null || map.get(num.charAt(right)) == null)     //不满足要求的数字
                return false;
            if(map.get(num.charAt(left)) != num.charAt(right))  //check一下是否真的中心对称
                return false;
            left++;
            right--;
        }
        return true;
    }
}
