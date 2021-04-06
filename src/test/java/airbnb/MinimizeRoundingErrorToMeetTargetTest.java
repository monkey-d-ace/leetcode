package airbnb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinimizeRoundingErrorToMeetTarget.class)
public class MinimizeRoundingErrorToMeetTargetTest {
    @Autowired
    private MinimizeRoundingErrorToMeetTarget minimizeRoundingErrorToMeetTarget;

    @Test
    public void testMinimizeRoundingErrorToMeetTarget() {
        String[] prices = new String[] {"0.700","2.800","4.900"};
        int target = 8;
        String ans = minimizeRoundingErrorToMeetTarget.minimizeError(prices, target);
        assertEquals("1.000", ans);

        prices = new String[] {"1.500","2.500","3.500"};
        target = 10;
        ans = minimizeRoundingErrorToMeetTarget.minimizeError(prices, target);
        assertEquals("-1", ans);
    }
}
