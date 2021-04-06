package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeetingRooms2.class)
public class MeetingRooms2Test {
    @Autowired
    private MeetingRooms2 meetingRooms2;

    @Test
    public void testMeetingRooms2() {
        int[][] intervals = new int[][] {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        int ans = meetingRooms2.minMeetingRooms(intervals);
        Assert.assertEquals(2, ans);

        intervals = new int[][] {
                {7, 10},
                {2, 4}
        };
        ans = meetingRooms2.minMeetingRooms(intervals);
        Assert.assertEquals(1, ans);
    }
}
