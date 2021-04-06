package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpToCidr.class)
public class IpToCidrTest {
    @Autowired
    private IpToCidr ipToCidr;

    @Test
    public void testIpToCidr() {
        String ip = "255.0.0.7";
        int n = 10;
        List<String> list = new ArrayList<>(Arrays.asList("255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"));
        Assert.assertEquals(list, ipToCidr.ipToCIDR(ip, n));
    }
}
