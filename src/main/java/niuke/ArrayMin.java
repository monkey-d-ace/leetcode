package niuke;

import java.util.*;

public class ArrayMin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> integers = new ArrayList<>();
        List<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        String s = scanner.nextLine();
        String[] array = s.split(" ");

        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length() && o1.substring(0, o2.length()).equals(o2)) {
                    if (o1.charAt(o2.length()) > o2.charAt(0)) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

                if (o2.length() > o1.length() && o2.substring(0, o1.length()).equals(o1)) {
                    if (o2.charAt(o1.length()) > o1.charAt(0)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return o1.compareTo(o2);
            }
        });
        for (String temp : array) {
            list.add(temp);
        }

        List<String> ll = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("0")) {
                ll.add(list.get(i));
                break;
            }
        }



        for (String temp : list) {
            if (temp.equals("0")) {
                ll.add(temp);
            }
        }
        for (String temp : list) {
            if (!temp.equals("0") && !temp.equals(ll.get(0))) {
                ll.add(temp);
            }
        }
        for (String temp : ll) {
            stringBuilder.append(temp);
        }
        System.out.println(stringBuilder.toString());
        scanner.close();
    }
}
