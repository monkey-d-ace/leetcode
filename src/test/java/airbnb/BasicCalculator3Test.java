package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicCalculator3.class)
public class BasicCalculator3Test {
    @Autowired
    private BasicCalculator3 calculator3;

    @Test
    public void testBasicCalculator3() {
        Assert.assertEquals(2, calculator3.calculate("1+1"));
        Assert.assertEquals(4, calculator3.calculate("6-4/2"));
        Assert.assertEquals(21, calculator3.calculate("2*(5+5*2)/3+(6/2+8)"));
        Assert.assertEquals(-12, calculator3.calculate("(2+6*3+5-(3*14/7+2)*5)+3"));
        Assert.assertEquals(0, calculator3.calculate("0"));
    }
}
