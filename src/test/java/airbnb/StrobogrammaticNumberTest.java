package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StrobogrammaticNumber.class)
public class StrobogrammaticNumberTest {
    @Autowired
    private StrobogrammaticNumber strobogrammaticNumber;

    @Test
    public void testStrbogrammaticNumber() {
        Assert.assertTrue(strobogrammaticNumber.isStrobogrammatic("69"));

        Assert.assertTrue(strobogrammaticNumber.isStrobogrammatic("88"));

        Assert.assertFalse(strobogrammaticNumber.isStrobogrammatic("962"));

        Assert.assertTrue(strobogrammaticNumber.isStrobogrammatic("1"));
    }
}
