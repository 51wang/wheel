package tuxedo.wheel.utility.array;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayUtilTest {
    @Test
    public void testArrayFilledByGenerator() {
        String[] expected = new String[] { "a", "b", "c", "d", "e", "f" };
        String[] actual = ArrayUtil.arrayFilledByGenerator(String.class, expected.length, i -> expected[i]);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testArrayFilledBySupplier() {
        String[] expected = new String[] { "a", "a", "a", "a" };
        String[] actual = ArrayUtil.arrayFilledBySupplier(String.class, expected.length, () -> new String("a"));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testArrayFilledByValue() {
        String[] expected = new String[] { "a", "a", "a", "a" };
        String[] actual = ArrayUtil.arrayFilledByValue(String.class, expected.length, "a");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testReverse() {
        __testReverse(new String[0], new String[0]);
        __testReverse(new String[] { "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d" });
        __testReverse(new String[] { "e", "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d", "e" });
    }

    private void __testReverse(String[] original, String[] expected) {
        ArrayUtil.reverse(original);
        Assert.assertEquals(original, expected);
    }

    @Test
    public void testCopyAndReverse() {
        __testCopyAndReverse(new String[] { "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d" });
        __testCopyAndReverse(new String[] { "e", "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d", "e" });
    }

    private void __testCopyAndReverse(String[] original, String[] expected) {
        String[] reversed = ArrayUtil.copyAndReverse(original);
        Assert.assertNotSame(reversed, original);
        Assert.assertEquals(reversed, expected);
    }

    @Test
    public void testAsList() {
        List<String> actual = ArrayUtil.asList(new String[] { "a", "b", "c", "d" });
        List<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");
        Assert.assertEquals(actual, expected);
    }
}
