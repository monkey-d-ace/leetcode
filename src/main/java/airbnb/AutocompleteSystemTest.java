package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
public class AutocompleteSystemTest {
    @Test
    public void testAutocompleteSystem() {
        AutocompleteSystem system = new AutocompleteSystem(new String[] {"i love you", "island","ironman", "i love leetcode"},
                new int[] {5,3,2,2});
        Assert.assertEquals(Arrays.asList("i love you", "island","i love leetcode"), system.input('i'));

        Assert.assertEquals(Arrays.asList("i love you","i love leetcode"), system.input(' '));

        Assert.assertEquals(Collections.EMPTY_LIST, system.input('a'));

        Assert.assertEquals(Collections.EMPTY_LIST, system.input('#'));
    }
}
