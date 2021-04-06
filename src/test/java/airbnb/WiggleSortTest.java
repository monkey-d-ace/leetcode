package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WiggleSort.class)
public class WiggleSortTest {
    @Autowired
    private WiggleSort wiggleSort;

    @Test
    public void testWiggleSort() {
        int[] nums = {3,5,2,1,6,4};
        wiggleSort.wiggleSort(nums);
        Assert.assertArrayEquals(new int[] {3,5,1,6,2,4}, nums);
    }
}
