package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SentenceScreenFitting.class)
public class SentenceScreenFittingTest {
    @Autowired
    private SentenceScreenFitting sentenceScreenFitting;

    @Test
    public void testSentenceScreenFitting() {
        String[] sentence = new String[] {"hello", "world"};
        Assert.assertEquals(1, sentenceScreenFitting.wordsTyping(sentence, 2, 8));

        sentenceScreenFitting.curorPostion = 0;
        sentenceScreenFitting.totalCount = 0;
        sentence = new String[] {"a", "bcd", "e"};
        Assert.assertEquals(2, sentenceScreenFitting.wordsTyping(sentence, 3, 6));

        sentenceScreenFitting.curorPostion = 0;
        sentenceScreenFitting.totalCount = 0;
        sentence = new String[] {"I", "had", "apple", "pie"};
        Assert.assertEquals(1, sentenceScreenFitting.wordsTyping(sentence, 4, 5));
    }
}
