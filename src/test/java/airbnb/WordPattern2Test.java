package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WordPattern2.class)
public class WordPattern2Test {
    @Autowired
    private WordPattern2 wordPattern2;

    @Test
    public void testWordPattern2() {
        String pattern = "abab";
        String s = "redblueredblue";
        Assert.assertTrue(wordPattern2.wordPatternMatch(pattern, s));

        wordPattern2.map.clear();
        pattern = "aaaa";
        s = "asdasdasdasd";
        Assert.assertTrue(wordPattern2.wordPatternMatch(pattern, s));

        wordPattern2.map.clear();
        pattern = "abab";
        s = "asdasdasdasd";
        Assert.assertTrue(wordPattern2.wordPatternMatch(pattern, s));

        wordPattern2.map.clear();
        pattern = "aabb";
        s = "xyzabcxzyabc";
        Assert.assertFalse(wordPattern2.wordPatternMatch(pattern, s));
    }

}
