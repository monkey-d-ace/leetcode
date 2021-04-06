package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinimumKnightMoves.class)
public class MinimumKnightMovesTest {
    @Autowired
    private MinimumKnightMoves minimumKnightMoves;

    @Test
    public void testMininumKnightMoves() {
        Assert.assertEquals(1, minimumKnightMoves.minKnightMoves(2, 1));

        Assert.assertEquals(4, minimumKnightMoves.minKnightMoves(5, 5));
    }
}
