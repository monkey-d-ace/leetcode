package airbnb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadNCharactersGivenRead42CallMultipleTimes.class)
public class ReadNCharactersGivenRead42CallMultipleTimesTest {
    @Autowired
    private ReadNCharactersGivenRead42CallMultipleTimes readNCharactersGivenRead42CallMultipleTimes;

    @Test
    public void testReadNCharactersGivenRead42CallMultipleTimes() {

    }
}
