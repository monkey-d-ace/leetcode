package airbnb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PalindromePermutation.class)
public class PalindromePermutationTest {
    @Autowired
    private PalindromePermutation palindromePermutation;

    @Test
    public void testPalindromePermutation() {
        Assert.assertFalse(palindromePermutation.canPermutePalindrome("code"));

        Assert.assertTrue(palindromePermutation.canPermutePalindrome("aab"));

        Assert.assertTrue(palindromePermutation.canPermutePalindrome("carerac"));
    }
}
