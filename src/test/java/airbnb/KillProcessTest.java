package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KillProcess.class)
public class KillProcessTest {
    @Autowired
    private KillProcess killProcess;

    @Test
    public void testKillProcess() {
        List<Integer> pid = Arrays.asList(1,3,10,5);
        List<Integer> ppid = Arrays.asList(3,0,5,3);
        Assert.assertEquals(Arrays.asList(5, 10), killProcess.killProcess(pid, ppid, 5));

        pid = Collections.singletonList(1);
        ppid = Collections.singletonList(0);
        Assert.assertEquals(Collections.singletonList(1), killProcess.killProcess(pid, ppid, 1));
    }
}
