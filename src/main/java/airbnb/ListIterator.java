package airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of lists, implement an iterator class to allow the client to traverse and remove elements in the list. This iterator should provide three public class member functions:
 *
 * class ListIterator implements Iterator<Integer> {
 *     public ListIterator(List<List<Integer>> lists) {
 *     }
 *
 *     // Return true or false if there is another element.
 *     public boolean hasNext();
 *
 *     // Return the value of the next element.
 *     public Integer next();
 *
 *     // Remove the last element returned by the iterator. That is, remove the element that the previous 'next()' returned.
 *     // This method can be called only once per call to next(), otherwise, an exception will be thrown.
 *     // See https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html#remove-- for details.
 *     public void remove();
 *  }
 *
 * The code should be well structured, and robust enough to handle any access pattern. Additionally, write code to demonstrate that the class can be used for the following basic scenarios:
 *
 * Print elements
 *
 * Given: [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]
 * Print: 1 2 3 4 5 6 7 8 9 10
 * Remove even elements
 *
 * Given: [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]
 * Should result in: [[],[1,3],[5],[],[],[],[7],[],[9],[],[]]
 * Print: 1 3 5 7 9
 */

public class ListIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        ListIterator iteratorList;
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        lists.add(list);
        list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);
        lists.add(list);
        list = new ArrayList<>();
        list.add(4);list.add(5);

        lists.add(list);
        list = new ArrayList<>();
        lists.add(list);
        list = new ArrayList<>();
        list.add(6);
        lists.add(list);
        list = new ArrayList<>();
        list.add(7);list.add(8);
        lists.add(list);
        list = new ArrayList<>();

        lists.add(list);
        list = new ArrayList<>();
        list.add(9);
        lists.add(list);
        list = new ArrayList<>();

        list.add(10);
        lists.add(list);

        list = new ArrayList<>();

        lists.add(list);
        iteratorList = new ListIterator(lists);

        while (iteratorList.hasNext()) {
            int i = iteratorList.next();
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();

        iteratorList = new ListIterator(lists);

        while (iteratorList.hasNext()) {
            int i = iteratorList.next();
            if (i % 2 == 0) {
                iteratorList.remove();
            }
        }

        iteratorList = new ListIterator(lists);
        while (iteratorList.hasNext()) {
            System.out.println(iteratorList.next());
        }

        System.out.println();
    }

    private Iterator<List<Integer>> rowIterator;
    private Iterator<Integer> colIterator;
    
    public ListIterator(List<List<Integer>> lists) {
        rowIterator = lists.iterator();
        colIterator = Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        while ((colIterator == null || !colIterator.hasNext()) && rowIterator.hasNext()) {
            colIterator = rowIterator.next().iterator();
        }
        return colIterator != null && colIterator.hasNext();
    }

    @Override
    public Integer next() {
        return colIterator.next();
    }

    public void remove() {
        while (colIterator == null && rowIterator.hasNext())
            colIterator = rowIterator.next().iterator();
        if (colIterator != null)
            colIterator.remove();
    }
}
