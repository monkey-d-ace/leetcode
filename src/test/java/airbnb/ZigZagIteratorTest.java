package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ZigZagIteratorTest {
    private ZigZagIterator zigZagIterator;

    @Test
    public void testZigZagIterator() {
        List<Integer> v1 = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> v2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        zigZagIterator = new ZigZagIterator(v1, v2);
        while (zigZagIterator.hasNext()) {
            System.out.print(zigZagIterator.next() + " ");
        }
        System.out.println();
    }
}
