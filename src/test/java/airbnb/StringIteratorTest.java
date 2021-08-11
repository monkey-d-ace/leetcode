package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StringIteratorTest {
    @Test
    public void testStringIterator() {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('e', iterator.next());
        Assert.assertEquals('e', iterator.next());
        Assert.assertEquals('t', iterator.next());
        Assert.assertEquals('C', iterator.next());
        Assert.assertEquals('o', iterator.next());
        Assert.assertEquals('d', iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals('e', iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(' ', iterator.next());
    }
}
