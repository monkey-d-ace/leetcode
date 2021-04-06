package airbnb;

import java.util.Scanner;
import java.util.Stack;

/**
 * Your input will be an ASCII string, and you will output a boolean, which indicates whether the string is valid xml.
 *
 * We simplify the xml format to only have content or tags.
 *
 * Content
 * Text which can contain any ascii characters EXCEPT < and >
 *
 * Tags
 * Tags come in two flavors. < and > must only appear as the start and end of a tag, and the tags cannot be empty. I.e <> and </> are invalid.
 *
 * The start-tag and end-tag elements must be correctly nested, with none missing and none overlapping. For example, text <a> text</a>, <a>text<b>other text</b></a> are valid, <a>text<b>other text</a></b> is not.
 *
 * The goal of this question is to simulate an xml validator. We will give you sample xml text and you should output wheter the text is valid xml or not.
 *
 * ###Example input/output
 *
 * Input
 * 	text
 * Output
 * 	true
 * Input
 * 	text<a>more text</a>
 * Output
 * 	true
 * Input
 * 	text</a>
 * Output
 * 	false
 * Input
 * 	<invalid<>text</invalid>
 * Output
 * 	false
 */
public class SimpleXMLValidator {
    Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String xml = scanner.nextLine();
        System.out.println(new SimpleXMLValidator().justify(xml));
        scanner.close();
    }
    public String justify(String xml) {
        String result = "Valid";

        if (xml == null || xml.isEmpty()) {
            return result;
        }

        for (int i = 0; i < xml.length(); ) {
             if (xml.startsWith("</", i)) {
                int j = xml.indexOf(">", i + 2);
                if (j == i + 2 || stack.isEmpty()) {
                    result = "parse error";
                    return result;
                } else {
                    String tag = stack.peek();
                    if (!xml.substring(i + 2, j).equals(tag)) {
                        result = "losing tag <" + xml.substring(i + 2, j) + ">";
                        return result;
                    } else {
                        i = j + 1;
                    }
                }
            } else if (xml.startsWith("<", i)) {
                 int j = xml.indexOf(">", i + 1);
                 if (j == i + 1) {
                     result = "parse error";
                     return result;
                 } else {
                     if (xml.substring(i + 1, j).contains(">") || xml.substring(i + 1, j).contains("<")) {
                         result = "parse error";
                         return result;
                     }
                     stack.push(xml.substring(i + 1, j));
                     i = j;
                 }
             } else {
                i++;
            }
        }
        if (!stack.isEmpty()) {
            result = "parse error";
            return result;
        }
        return result;
    }
}
