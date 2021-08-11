package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RearrangeStringKDistanceApart.class)
public class RearrangeStringKDistanceApartTest {
    @Autowired
    private RearrangeStringKDistanceApart apart;

    @Test
    public void testRearrangeStringKDistanceApart() {
        Assert.assertEquals("acbacb", apart.rearrangeString("aabbcc", 3));
        Assert.assertEquals("", apart.rearrangeString("aaabc", 3));
        Assert.assertEquals("abcabcad", apart.rearrangeString("aaadbbcc", 2));
    }
}
