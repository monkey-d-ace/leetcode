package airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement Queue with Fixed Size of Arrays
 * AirBnB Interview Question
 */
public class ImplementQueueWithFixArray {
    public static void main(String[] args) {
        ImplementQueueWithFixArray queue = new ImplementQueueWithFixArray(5);
        queue.offer(1);
        queue.offer(2);
        int res = queue.poll();
        System.out.println(res);

        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        res = queue.poll();
        System.out.println(res);
        res = queue.poll();
        System.out.println(res);
    }


    private int fixedSize;
    private int count;
    private int head;
    private int tail;
    private List<Object> headList;
    private List<Object> tailList;

    public ImplementQueueWithFixArray(int fixedSize) {
        this.fixedSize = fixedSize;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        this.headList = new ArrayList<>();
        this.tailList = this.headList;
    }

    public void offer(int num) {
        if (tail == fixedSize - 1) {
            List<Object> newList = new ArrayList<>();
            newList.add(num);
            tailList.add(newList);
            tailList = (List<Object>)tailList.get(tail);
            tail = 0;
        } else {
            tailList.add(num);
        }
        count++;
        tail++;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }

        int num = (int)headList.get(head);
        head++;
        count--;

        if (head == fixedSize - 1) {
            List<Object> newList = (List<Object>)headList.get(head);
            headList.clear();
            headList = newList;
            head = 0;
        }
        return num;
    }

    public int size() {
        return count;
    }
}
