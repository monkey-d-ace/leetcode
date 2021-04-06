package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 2020.09.25 面试①题
 *
 * 588. 设计内存文件系统
 * 设计一个内存文件系统，模拟以下功能：
 *
 * ls： 以字符串的格式输入一个路径。如果它是一个文件的路径，那么函数返回一个列表，仅包含这个文件的名字。如果它是一个文件夹的的路径，那么返回该 文件夹内 的所有文件和子文件夹的名字。你的返回结果（包括文件和子文件夹）应该按字典序排列。
 *
 * mkdir：输入一个当前不存在的 文件夹路径 ，你需要根据路径名创建一个新的文件夹。如果有上层文件夹路径不存在，那么你也应该将它们全部创建。这个函数的返回类型为 void 。
 *
 * addContentToFile： 输入字符串形式的 文件路径 和 文件内容 。如果文件不存在，你需要创建包含给定文件内容的文件。如果文件已经存在，那么你需要将给定的文件内容 追加 在原本内容的后面。这个函数的返回类型为 void 。
 *
 * readContentFromFile： 输入 文件路径 ，以字符串形式返回该文件的 内容 。
 *
 *
 *
 * 示例:
 *
 * 输入:
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 *
 * 输出:
 * [null,[],null,null,["a"],"hello"]
 *
 * 解释:
 * filesystem
 *
 *
 * 注意:
 *
 * 你可以假定所有文件和文件夹的路径都是绝对路径，除非是根目录 / 自身，其他路径都是以 / 开头且 不 以 / 结束。
 * 你可以假定所有操作的参数都是有效的，即用户不会获取不存在文件的内容，或者获取不存在文件夹和文件的列表。
 * 你可以假定所有文件夹名字和文件名字都只包含小写字母，且同一文件夹下不会有相同名字的文件夹或文件。
 */
public class DesignInMemoryFileSystem {
    class FileOrDir {
        HashMap<String, FileOrDir> files = new HashMap<>();
        boolean isFile = false;
        String content = "";
    }
    FileOrDir root;
    public DesignInMemoryFileSystem() {
        root = new FileOrDir();
    }

    public List<String> ls(String path) {
        FileOrDir t = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                t = t.files.get(d[i]);
            }
            if (t.isFile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List <String> res_files = new ArrayList <> (t.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        FileOrDir t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.files.containsKey(d[i])) {
                t.files.put(d[i], new FileOrDir());
            }
            t = t.files.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        FileOrDir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }

        if (!t.files.containsKey(d[d.length - 1])) {
            t.files.put(d[d.length - 1], new FileOrDir());
        }

        t = t.files.get(d[d.length - 1]);
        t.isFile = true;
        t.content = t.content + content;
    }

    public String readContentFromFile(String filePath) {
        FileOrDir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length; i++) {
            t = t.files.get(d[i]);
        }
        return t.content;
    }
}
/**
 * 使用统一的文件夹文件列表 [Accepted]
 * 这个方法与前一种方法的不同点在于现在文件夹数据结构只有一个统一的 filesfiles 哈希表，它保存了当前路径下所有的文件和子文件夹。除此以外，每个条目都有一个变量 isfileisfile，如果为 True 表示当前是一个文件，否则是一个文件夹。进一步的，因为我们将文件夹和文件统一保存，我们还需要一个 contentcontent 的条目，如果 isfileisfile 为 True 那么它保存了当前文件的内容。对于子文件夹，contentcontent 为空。
 *
 * 下图说明了上面例子中的前 2 层文件结构。
 *
 *
 *
 * 实现所有命令的方法与上一种解法一致，除了在 addContentToFile 和 mkdir 操作中我们对文件夹和文件的操作都需要在同一个 filesfiles 哈希表中进行。进一步的，对于 ls，我们不需要分别从文件夹列表和文件列表中获取条目名字，因为它们现在用同一个数据结构维护。
 *
 */