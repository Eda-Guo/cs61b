import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome() {
        String a = "abba";
        String b = "acta";
        String c = "a";
        String d = "";
        assertTrue(palindrome.isPalindrome(a));
        assertFalse(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(d));
    }
    @Test
    public void testIsPalindromeOffByOne() {
        assertTrue(palindrome.isPalindrome("f%a&e", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abcbB", new OffByOne()));
    }
    @Test
    public void testIsPalindromeOffByN() {
        assertTrue(palindrome.isPalindrome("afaf", new OffByN(5)));
        assertFalse(palindrome.isPalindrome("abcba", new OffByN(5)));
    }
}
