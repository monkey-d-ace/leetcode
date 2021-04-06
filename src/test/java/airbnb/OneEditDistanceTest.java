package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OneEditDistance.class)
public class OneEditDistanceTest {
    @Autowired
    private OneEditDistance oneEditDistance;

    @Test
    public void testOneEditDistance() {
        String s = "ab", t = "acb";
        Assert.assertTrue(oneEditDistance.isOneEditDistance(s, t));

        Assert.assertFalse(oneEditDistance.isOneEditDistance("cab", "t"));

        Assert.assertTrue(oneEditDistance.isOneEditDistance("1203", "1213"));
    }
}
