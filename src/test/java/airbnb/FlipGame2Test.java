package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlipGame2.class)
public class FlipGame2Test {
    @Autowired
    private FlipGame2 flipGame2;

    @Test
    public void testFlipGame2() {
        Assert.assertTrue(flipGame2.canWin("++++"));
        Assert.assertFalse(flipGame2.canWin("+"));
    }
}
