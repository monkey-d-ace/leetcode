package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PourWater.class)
public class PourWaterTest {
    @Autowired
    private PourWater pourWater;

    @Test
    public void testPourWater() {
        int[] heights = new int[] {2,1,1,2,1,2,2};
        int V = 4;
        int K = 3;
        Assert.assertArrayEquals(new int[] {2,2,2,3,2,2,2}, pourWater.pourWater(heights, V, K));
        heights = new int[] {1, 2, 3, 4};
        V = 2;
        K = 2;
        Assert.assertArrayEquals(new int[] {2, 3, 3, 4}, pourWater.pourWater(heights, V, K));
        heights = new int[] {3, 1, 3};
        V = 5;
        K = 1;
        Assert.assertArrayEquals(new int[] {4, 4, 4}, pourWater.pourWater(heights, V, K));
    }
}
