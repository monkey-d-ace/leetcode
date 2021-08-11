package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SplitArrayWithEqualSum.class)
public class SplitArrayWithEqualSumTest {
    @Autowired
    private SplitArrayWithEqualSum splitArrayWithEqualSum;

    @Test
    public void SplitArrayWithEqualSum() {
        Assert.assertTrue(splitArrayWithEqualSum.splitArray(new int[] {1, 2, 1, 2, 1, 2, 1}));
    }
}
