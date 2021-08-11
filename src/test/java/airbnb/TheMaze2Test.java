package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheMaze2.class)
public class TheMaze2Test {
    @Autowired
    private TheMaze2 theMaze2;

    @Test
    public void testTheMaze2() {
        int[][] maze = new int[][] {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        Assert.assertEquals(12, theMaze2.shortestDistance(maze, new int[] {0, 4}, new int[] {4, 4}));

        Assert.assertEquals(-1, theMaze2.shortestDistance(maze, new int[] {0, 4}, new int[] {3, 2}));
    }
}
