package airbnb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 751. IP 到 CIDR
 * 给定一个起始 IP 地址 ip 和一个我们需要包含的 IP 的数量 n，返回用列表（最小可能的长度）表示的 CIDR块的范围。
 *
 * CIDR 块是包含 IP 的字符串，后接斜杠和固定长度。例如：“123.45.67.89/20”。固定长度 “20” 表示在特定的范围中公共前缀位的长度。
 *
 * 示例 1：
 *
 * 输入：ip = "255.0.0.7", n = 10
 * 输出：["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * 解释：
 * 转换为二进制时，初始IP地址如下所示（为清晰起见添加了空格）：
 * 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * 地址 "255.0.0.7/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 在这里只有这一个地址。
 *
 * 地址 "255.0.0.8/29" 表示与给定地址有相同的 29 位前缀的所有地址：
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * 有相同的 29 位前缀的地址如下：
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * 11111111 00000000 00000000 00001011
 * 11111111 00000000 00000000 00001100
 * 11111111 00000000 00000000 00001101
 * 11111111 00000000 00000000 00001110
 * 11111111 00000000 00000000 00001111
 *
 * 地址 "255.0.0.16/32" 表示与给定地址有相同的 32 位前缀的所有地址，
 * 这里只有 11111111 00000000 00000000 00010000。
 *
 * 总之，答案指定了从 255.0.0.7 开始的 10 个 IP 的范围。
 *
 * 有一些其他的表示方法，例如：
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * 但是我们的答案是最短可能的答案。
 *
 * 另外请注意以 "255.0.0.7/30" 开始的表示不正确，
 * 因为其包括了 255.0.0.4 = 11111111 00000000 00000000 00000100 这样的地址，
 * 超出了需要表示的范围。
 *
 *
 * 注：
 *
 * ip 是有效的 IPv4 地址。
 * 每一个隐含地址 ip + x (其中 x < n) 都是有效的 IPv4 地址。
 * n 为整数，范围为 [1, 1000]。
 */
@Component
public class IpToCidr {
    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                    33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }

    private long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }
    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
                x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }
}
/**
 * 方法一：
 * 若是手写计算答案则相对简单，写代码棘手的部分是所涉及的位操作。
 * 我们思考一个问题：对于所需的 n 个 ip 地址，以及从起始 ip 地址开始且在范围内的 ip 地址，哪些 CIDR 块能够表示在范围内的大部分 ip 地址？用贪心的思想是可行的，我们可以一直重复这个过程，直到我们包括 n 个 ip 地址，所以我们应该尽可能的使用一个大的 CIDR 块。
 * 算法：
 *
 * 我们需要将 ip 地址转换为长整数，通过一些基本的操作来实现这个功能--具体看代码实现方式。
 * 然后，将 255.0.0.24 这样的 ip 地址转换为 start，它以二进制 00011000 结尾。如果 n>=8，那么我们应该使用整个块 255.0.0.24/29。否则，我们只取满足 2^x>=n2
 * x
 *  >=n x 的最小值 。
 * 在一般情况下啊，我们使用 n 和 start&-start（start 的最低位）的位长度来计算能表示 2^{32 - \text{mask}}2
 * 32−mask
 *   个 ip 地址的掩码。然后，我们动态调整 start 和 n。
 * 在 Java 和 C++ 中，我们应该小心使用长整数来表示转换后的 IP 地址，因为该数字可能超过 2^{31}2
 * 31
 *  。
 *
 * 复杂度分析
 *
 * 时间复杂度：O(N)O(N)。其中 NN 表示的是 nums 的长度
 * 空间复杂度：O(1)O(1)。
 */
