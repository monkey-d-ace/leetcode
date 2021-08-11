package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MaximumDistanceInArrays.class)
public class MaximumDistanceInArraysTest {
    @Autowired
    private MaximumDistanceInArrays inArrays;

    @Test
    public void testMaximumDistanceInArrays() {
        List<List<Integer>> arrays = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(1, 2, 3)
        );
        Assert.assertEquals(4, inArrays.maxDistance(arrays));
    }
}
