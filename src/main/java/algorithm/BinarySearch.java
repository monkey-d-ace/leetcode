package algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        Integer[] a = {1, 2, 2, 4, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(a, 5));
    }

    private static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a, AnyType x) {
        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
