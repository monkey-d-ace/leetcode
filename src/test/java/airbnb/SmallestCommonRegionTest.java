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
@SpringBootTest(classes = SmallestCommonRegion.class)
public class SmallestCommonRegionTest {
    @Autowired
    private SmallestCommonRegion smallestCommonRegion;

    @Test
    public void testSmallestCommonRegion() {
        List<List<String>> regions = Arrays.asList(
                Arrays.asList("Earth","North America","South America"),
                Arrays.asList("North America","United States","Canada"),
                Arrays.asList("United States","New York","Boston"),
                Arrays.asList("Canada","Ontario","Quebec"),
                Arrays.asList("South America","Brazil")
        );
        String region1 = "Quebec";
        String region2 = "New York";
        Assert.assertEquals("North America", smallestCommonRegion.findSmallestRegion(regions, region1, region2));
    }
}
