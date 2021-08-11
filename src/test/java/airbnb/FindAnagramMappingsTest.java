package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FindAnagramMappings.class)
public class FindAnagramMappingsTest {
    @Autowired
    private FindAnagramMappings mappings;

    @Test
    public void testFindAnagramMappings() {
        int[] A = new int[] {
                12, 28, 46, 32, 50
        };
        int[] B = new int[] {
                50, 12, 32, 46, 28
        };
        Assert.assertArrayEquals(new int[] {1, 4, 3, 2, 0}, mappings.anagramMappings(A, B));
    }
}
