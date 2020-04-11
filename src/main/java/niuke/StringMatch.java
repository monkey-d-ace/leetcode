package niuke;

import java.util.Scanner;

public class StringMatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String aa = scanner.nextLine();
        String parent = aa;
        String match = scanner.nextLine();
        int sum = 0;
        int start = 0, end = match.length() - 1;
        while (true) {
            if (match.indexOf(start) == '*') {
                start += 1;
            } else if (match.indexOf(start) == '?') {
                start += 1;
            } else if (match.indexOf(end) == '*') {
                end -= 1;
            } else if (match.indexOf(end) == '?') {
                end -= 1;
            } else {
                if (isMatch(parent, match)) {
                    sum += 1;

                    parent = parent.substring(parent.indexOf(match.split("")[0]) + 1);

                    parent = parent.substring(parent.indexOf(match.split("")[0]) + 1, parent.indexOf(match.split("")[match.length() - 1]) + 1);

                } else {

                    break;
                }
            }
        }
        System.out.println(sum);
        scanner.close();
    }

    private static boolean isMatch(String p, String m) {
        String[] array = m.split("");
        return (p.contains(array[0]) && p.contains(array[m.length() - 1]) && p.indexOf(array[0]) < p.indexOf(array[m.length() - 1]));
    }
}
