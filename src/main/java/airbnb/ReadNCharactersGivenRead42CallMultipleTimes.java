package airbnb;

/**
 * 158. 用 Read4 读取 N 个字符 II
 * 给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。注意：你的 read 方法可能会被调用多次。
 *
 * read4 的定义：
 *
 * read4 API 从文件中读取 4 个连续的字符，然后将这些字符写入缓冲区数组 buf4 。
 *
 * 返回值是读取的实际字符数。
 *
 * 请注意，read4() 有其自己的文件指针，类似于 C 中的 FILE * fp 。
 *
 * 参数类型: char[] buf4
 * 返回类型: int
 *
 * 注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
 * 下列是一些使用 read4 的例子：
 *
 *
 *
 * File file("abcde"); // 文件名为 "abcde"， 初始文件指针 (fp) 指向 'a'
 * char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
 * read4(buf4); // read4 返回 4。现在 buf4 = "abcd"，fp 指向 'e'
 * read4(buf4); // read4 返回 1。现在 buf4 = "e"，fp 指向文件末尾
 * read4(buf4); // read4 返回 0。现在 buf4 = ""，fp 指向文件末尾
 * read 方法：
 *
 * 通过使用 read4 方法，实现 read 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组 buf 中。您 不能 直接操作文件。
 *
 * 返回值为实际读取的字符。
 *
 * read 的定义：
 *
 * 参数:   char[] buf, int n
 * 返回值: int
 *
 * 注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
 *
 *
 * 示例 1：
 *
 * File file("abc");
 * Solution sol;
 * // 假定 buf 已经被分配了内存，并且有足够的空间来存储文件中的所有字符。
 * sol.read(buf, 1); // 当调用了您的 read 方法后，buf 需要包含 "a"。 一共读取 1 个字符，因此返回 1。
 * sol.read(buf, 2); // 现在 buf 需要包含 "bc"。一共读取 2 个字符，因此返回 2。
 * sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
 * 示例 2：
 *
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 4); // 当调用了您的 read 方法后，buf 需要包含 "abc"。 一共只能读取 3 个字符，因此返回 3。
 * sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
 *
 *
 * 提示：
 *
 * 你 不能 直接操作该文件，文件只能通过 read4 获取而 不能 通过 read。
 * read  函数可以被调用 多次。
 * 请记得 重置 在 Solution 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定目标缓存数组 buf 保证有足够的空间存下 n 个字符。
 * 保证在一个给定测试用例中，read 函数使用的是同一个 buf。
 */
public class ReadNCharactersGivenRead42CallMultipleTimes extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    private char[] fileBuf = new char[4];   // read4 读到的数据存到这里
    private int readOffset = 0; // 指向 fileBuf 尚未使用的开头索引
    private int bufSize = 0;    // 最近一次从 read4 读到的数据有多少个

    public int read(char[] buf, int n) {
        for (int i = 0; i < n; i++) {
            char nextChar = getNextCharFromFile();
            if (nextChar == 0) {
                return i;
            } else {
                buf[i] = nextChar;
            }
        }
        return n;
    }

    public char getNextCharFromFile() {
        // 比如最近一次 read4 读到的有效数据有 2 个，此时 readOffset == 2，说明 fileBuf 已经没有未使用的数据，需要重新 read4
        if (readOffset == bufSize) {
            bufSize = read4(fileBuf);
            readOffset = 0;
            if (bufSize == 0) {
                return 0;
            }
        }
        // readOffset 永远指向尚未读取的数据的最开头
        return fileBuf[readOffset++];
    }

}
/**
 * 解题思路
 * 用一个容量为 4 的数组 fileBuf 存放最近一次 read4 读取到的数据。
 *
 * 用一个变量 readOffset 指向 fileBuf 尚未使用的开头数据索引。
 *
 * 用一个变量 bufSize 表示最近一次 read4 读取到的数据字符个数。
 *
 * 大致算法逻辑如下：
 *
 * 每次调用 read(char[] buf, int n) 时，都是调用 n 次 getNextCharFromFile()。
 *
 * getNextCharFromFile() 逻辑如下：
 *
 * 当 readOffset < bufSize，说明最近一次读取到 fileBuf 的数据还没用完，从 fileBuf 中取出一个字符，然后 readOffset++；
 *
 * 当 readOffset == bufSize，说明最近一次 read4 读到的数据已经用完了，于是再调用一次 read4，重置 readOffset 为 0，bufSize = read4(fileBuf)。接着从 fileBuf 拿出一个字符返回出去，readOffset++。
 *
 * 作者：klb
 * 链接：https://leetcode-cn.com/problems/read-n-characters-given-read4-ii-call-multiple-times/solution/158-yong-read4-du-qu-n-ge-zi-fu-ii-by-klb/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */