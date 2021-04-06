package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StringsDifferByOneCharacter.class)
public class StringsDifferByOneCharacterTest {
    @Autowired
    private StringsDifferByOneCharacter stringsDifferByOneCharacter;

    @Test
    public void testStringsDifferByOneCharacter() {
        String[] dict = new String[] {"abcd","acbd", "aacd"};
        Assert.assertTrue(stringsDifferByOneCharacter.differByOne(dict));
        dict = new String[] {"ab","cd","yz"};
        Assert.assertFalse(stringsDifferByOneCharacter.differByOne(dict));
        dict = new String[] {"abcd","cccc","abyd","abab"};
        Assert.assertTrue(stringsDifferByOneCharacter.differByOne(dict));
    }
}
