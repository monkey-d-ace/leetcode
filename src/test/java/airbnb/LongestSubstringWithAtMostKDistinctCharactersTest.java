package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LongestSubstringWithAtMostKDistinctCharacters.class)
public class LongestSubstringWithAtMostKDistinctCharactersTest {
    @Autowired
    private LongestSubstringWithAtMostKDistinctCharacters distinctCharacters;

    @Test
    public void testLongestSubstringWithAtMostKDistinctCharacters() {
        Assert.assertEquals(3, distinctCharacters.lengthOfLongestSubstringKDistinct("eceba", 2));

        Assert.assertEquals(2, distinctCharacters.lengthOfLongestSubstringKDistinct("aa", 1));
    }
}
